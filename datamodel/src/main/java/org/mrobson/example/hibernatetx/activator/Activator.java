/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mrobson.example.hibernatetx.activator;

import org.apache.log4j.Logger;
//import org.osgi.framework.*;
//import org.osgi.framework.ServiceReference;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
//import org.osgi.framework.FrameworkUtil;
//import org.apache.aries.jpa.container.context.PersistenceContextProvider;
//import io.fabric8.api.ServiceLocator;
//import java.util.concurrent.TimeUnit;
/**
 * @author <a href="mailto:mrobson@redhat.com">Matthew Robson</a>
 * 
 * Nov 12, 2015
 */
                                                                                                                                                                                                                                                                                                                                                                                  
public class Activator implements BundleActivator {                                                                                                                                        
                    
	private Logger log = Logger.getLogger(Activator.class);
        private BundleContext context;
        //private PersistenceContextProvider service;
	
	public void start(BundleContext context) throws Exception {                                                                                                                            
        /*Bundle[] bundles = context.getBundles();                                                                                                                                           
        try{                                                                                                                                                                               
            for(Bundle b : bundles){                                                                                                                                                       
                    if(b.getSymbolicName().equalsIgnoreCase("org.hibernate.osgi")) {
                    	if (b.getState() == Bundle.ACTIVE) {
                            log.info(b.getSymbolicName() + " is active, stopping and starting");
                    		b.stop();
                    		b.start();
                    	}
                    }
            }                                                                                                                                                                              
        } catch (BundleException var6) {                                                                                                                                                   
        }*/
	log.info("Activator Start");
	//this.context = context;
	/*ServiceReference reference = context.getServiceReference(PersistenceContextProvider.class.getName());
	service = (PersistenceContextProvider) context.getService(reference);*/
	//ServiceLocator.awaitService(FrameworkUtil.getBundle(PersistenceContextProvider.class).getBundleContext(), PersistenceContextProvider.class, 30, TimeUnit.SECONDS);
	log.info("Activator Service Done");
    }                                                                                                                             
    public void stop(BundleContext context) throws Exception {}                                                                                                                                                                                    
}
