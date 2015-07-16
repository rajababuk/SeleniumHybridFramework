/*************************************************************************************
 *
 * 	Copyright (C) 2015 Ashvini Sharma
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the 
 *  License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 *  either express or implied. See the License for the specific language governing permissions 
 *  and limitations under the License.
 *  
 ******************************************************************************/


package com.selenium.model;

import org.apache.log4j.Logger;

import com.selenium.dto.GlobalBean;
import com.selenium.utility.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This class is initialization class therefore contains main function. This class will work as controller. 
 * It trigger the framework and will control entire process until execution finished. 
 * Defines preconditions and post conditions of the framework.   
 * 
 * @author ashvini sharma
 * @version 1.0
 * 
 * Last Edited by ashvini sharma on @date 6-June-2015
 * @version 1.0
 *
 */


public class GlobalModel {
	
	//Logging details in log.property file 
	static Logger log = Logger.getLogger(GlobalModel.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	@BeforeClass
	public static void intialTestSetup() {
		//Reading configuration file and assign to global variable values from configuration file.
		ReadProjectConfiguration Read = new ReadProjectConfiguration();
		Read.readProjectConfigure();	
	}
	
	@Test
	public void RunSuite() {
		XMLparsing xml = new XMLparsing();
		xml.ParseTestScenarios();
	}
	
	@AfterClass
	public static void processTestReport() {
		
	}
	
	
}