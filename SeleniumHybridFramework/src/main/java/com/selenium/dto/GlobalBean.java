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

package com.selenium.dto;

/**
 * This class is used to read place getter and setter for 'ReadProjectConfiguration' 
 * 
 * @author ashvini sharma
 * @version 1.0
 * 
 * Last Edited by ashvini sharma on @date 6-June-2015
 * @version 1.0
 *
 */

public class GlobalBean {
	
	// List of variable to read configuration file
	private String release_num;
	private String browser;
	private String testscenrios_xml;
	private String testcase_xml;
	private String testdata_xml;
	
	//Getter for configuration file
	public String getRelease_num() {
		return release_num;
	}
	
	public String getbrowser() {
		return browser;
	}
	
	public String getTestScenrios_xml() {
		return testscenrios_xml;
	}
	
	public String getTestCase_xml() {
		return testcase_xml;
	}
	
	public String getTestData_xml() {
		return testdata_xml;
	}
	
	
	
	//List of variables to read from XL file
	
	
}


















