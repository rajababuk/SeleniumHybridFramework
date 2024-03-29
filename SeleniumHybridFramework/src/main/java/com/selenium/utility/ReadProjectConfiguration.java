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

package com.selenium.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This class is used to read configuration details, defined in file /resources/Project.configuration.
 * This file will be called only once during the entire suite run. 
 * This class object is used by main/suit runner file, which is under model package. 
 * 
 * @author ashvini sharma
 * @version 1.0
 * 
 * @Standards 5-50-500
 * Number of Method = 1
 * Maximum Number of Lines in a method = 29
 * Number of lines in file = 74
 * 
 * Last Edited by ashvini sharma on @date 8-June-2015
 * @version 1.0
 *
 */

public class ReadProjectConfiguration {

	//Set to log details in log file according to "log4j.propereties" file.. 
	static Logger log = Logger.getLogger(ReadProjectConfiguration.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	
	//
	//public static String release_num;
	
	//To read property file format (log4j.properties) used Property class.
	Properties conf_file = new Properties();

	/*
	 * This method is to read configuration file (project.configuration) and 
	 * set values through bean file. 
	 */
	public void readProjectConfigure() {
		
		//Read configuration file in try catch block.
		try {
			FileInputStream read = new FileInputStream("resources/Project.Configuration");
			conf_file.load(read);
		} catch (IOException e) {
			log.debug("Config file reading error", e);
		}
		
		//Assign values to global variables 
		GlobalVar.release_num=(conf_file.getProperty("Release"));
		GlobalVar.browser=(conf_file.getProperty("Browser"));
		GlobalVar.testscenarios_xml=(conf_file.getProperty("TestScenarios"));
		GlobalVar.testcase_xml=(conf_file.getProperty("TestCases"));
		GlobalVar.testdata_xml=(conf_file.getProperty("TestData"));
		GlobalVar.DBConnectionString=(conf_file.getProperty("DBConnectionString"));
		GlobalVar.user_id=(conf_file.getProperty("DBUserID"));
		GlobalVar.password=(conf_file.getProperty("DBPassword"));
	}
			
}
