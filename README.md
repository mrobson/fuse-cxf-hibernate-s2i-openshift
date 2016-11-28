CXF Hibernate s2i: demonstrates RESTful web services with CXF and Hibernate Persistence with an Openshift s2i Build
===================================================================================================================
Author: Matt Robson 

Technologies: CXF, Hibernate, Openshift, Docker, Karaf

Product: Fuse 6.3, Openshift 3.3, Fuse Integration Service(FIS) 2.0

Breakdown
---------
This code example shows how to use FIS 2.0 Source 2 Image to produce a Karaf based docker image for an Openshift deployment. It also includes an Openshift template which defines a BuildConfig, DeploymentConfig, Service, Route and ImageStream.  In this example, we demonstrate the integration of RESTful (JAX-RS) web services using CXF and entity persistence with Aries JPA + Hibernate.  It also demonstrates how to wire an EntityManager to a JPA Context and on to a Service. Other interesting aspects include the use of the Fuse BOM, Karaf Assembly, Swagger and ExceptionMapper.

For more information see:

https://access.redhat.com/site/documentation/JBoss_Fuse/ for more information about using JBoss Fuse

https://access.redhat.com/documentation/en/red-hat-xpaas/version-0/red-hat-xpaas-fuse-integration-services-image/ for more information on JBoss Fuse Integration Services

https://access.redhat.com/documentation/en/openshift-container-platform/ for more information about Openshift Container Platform

https://access.redhat.com/documentation/en/red-hat-container-development-kit/ for more information about the Openshift Container Development Kit

System Requirements
-------------------
Before building and running this quick start you need:

Maven 3.3 or higher

Java 1.8

Openshift Container Platform 3.3

Build and Deploy
----------------
1) clone this project

	git clone https://github.com/mrobson/fuse-cxf-hibernate-s2i-openshift.git

2) change to project directory 

	cd fuse-cxf-hibernate-s2i-openshift

3) login to Openshift

	oc login -u <username>

4) create a new project

	oc new-project cxfhibernate-s2i-project

5) create the template

	oc create -f cxfhibernate-template.json

6) create the new application

	oc new-app s2i-hibernate-cxf-karaf

7) this will kick off a new build

	--> Deploying template s2i-hibernate-cxf-karaf

		s2i-hibernate-cxf-karaf
		---------
		CXF Hibernate s2i in Karaf container.
		* With parameters:
			* Application Name=s2i-hibernate-cxf-karaf
			* Git Repository URL=https://github.com/mrobson/fuse-cxf-hibernate-s2i-openshift.git
			* Git Reference=master
			* Service Name=s2i-hibernate-cxf-karaf
			* Builder version=2.0
			* Application Version=1.0-SNAPSHOT
			* Maven Arguments=install -DskipTests -Dfabric8.skip -e -B
			* Extra Maven Arguments=
			* Maven build directory=hibernatecxf-s2ikaraf/target/
			* Image Stream Namespace=openshift
			* Git Build Secret=EUvQRl2x3iAyA7GflYjTI8uqGkQ2d4hWV6Ofvloy # generated

	--> Creating resources with label app=s2i-hibernate-cxf-karaf ...
		route "s2i-hibernate-cxf-karaf-route" created
		service "s2i-hibernate-cxf-karaf" created
		imagestream "s2i-hibernate-cxf-karaf" created
		buildconfig "s2i-hibernate-cxf-karaf" created
		deploymentconfig "s2i-hibernate-cxf-karaf" created
	--> Success
		Build scheduled, use 'oc logs -f bc/s2i-hibernate-cxf-karaf' to track its progress.
		Run 'oc status' to view your app.

8) watch the build progress - this will clone the source, build the project, create the karaf assemply, create the docker image and push it to the internal registry

	oc logs -f <build_pods_name>

9) after the build finishes, a deploy pod will spin up and launch the container

	s2i-hibernate-cxf-karaf-<num>-<id>

10) watch the pod start

	oc logs -f s2i-hibernate-cxf-karaf-<num>-<id>

11) once the pod is running, you're done

	[cloud-user@osemaster2 ~]$ oc get pods
	NAME                              READY     STATUS      RESTARTS   AGE
	s2i-hibernate-cxf-karaf-1-bdaje   1/1       Running     0          2m

12) check the health and readiness

	curl http://s2i-hibernate-cxf-karaf.cxfhibernate-s2i-project.svc.cluster.local:8181/health-check
	curl http://s2i-hibernate-cxf-karaf.cxfhibernate-s2i-project.svc.cluster.local:8181/readiness-check

Usage
-----

1) confirm your route URL

	[cloud-user@osemaster2 fuse-cxf-hibernate-s2i-openshift]$ oc get route
	NAME                            HOST/PORT                                                 PATH      SERVICES                  PORT      TERMINATION
	s2i-hibernate-cxf-karaf-route   s2i-hibernate-cxf-karaf-route-test.apps.mrobson.ose.com             s2i-hibernate-cxf-karaf   <all>

2) add a new person, using the route URL

	curl -X POST -d @cxfhibernate/src/test/resources/newperson.json --header "Content-Type:application/json" http://<route_url>/cxf/hibernates2i/person/addPerson

3) get a person

	curl http://<route_url>/cxf/hibernates2i/person/findById/1

Remove the Services
-------------------

1) delete everything

	oc delete all oc delete all -l project=s2i-hibernate-cxf-karaf
	oc delete template s2i-hibernate-cxf-karaf
