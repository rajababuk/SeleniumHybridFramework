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
	private String DBServer;
	private String DBConnectionString;
	private String user_id;
	private String password;
	
	/**
	 * @return the dBServer
	 */
	public String getDBServer() {
		return DBServer;
	}

	/**
	 * @param dBServer the dBServer to set
	 */
	public void setDBServer(String dBServer) {
		DBServer = dBServer;
	}

	/**
	 * @return the dBConnectionString
	 */
	public String getDBConnectionString() {
		return DBConnectionString;
	}

	/**
	 * @param dBConnectionString the dBConnectionString to set
	 */
	public void setDBConnectionString(String dBConnectionString) {
		DBConnectionString = dBConnectionString;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the release_num
	 */
	public String getRelease_num() {
		return release_num;
	}
	/**
	 * @param release_num the release_num to set
	 */
	public void setRelease_num(String release_num) {
		this.release_num = release_num;
	}
	
	/**
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	
	/**
	 * @return the testscenrios_xml
	 */
	public String getTestscenrios_xml() {
		return testscenrios_xml;
	}
	/**
	 * @param testscenrios_xml the testscenrios_xml to set
	 */
	public void setTestscenrios_xml(String testscenrios_xml) {
		this.testscenrios_xml = testscenrios_xml;
	}
	
	/**
	 * @return the testcase_xml
	 */
	public String getTestcase_xml() {
		return testcase_xml;
	}
	/**
	 * @param testcase_xml the testcase_xml to set
	 */
	public void setTestcase_xml(String testcase_xml) {
		this.testcase_xml = testcase_xml;
	}


	/**
	 * @return the testdata_xml
	 */
	public String getTestdata_xml() {
		return testdata_xml;
	}
	/**
	 * @param testdata_xml the testdata_xml to set
	 */
	public void setTestdata_xml(String testdata_xml) {
		this.testdata_xml = testdata_xml;
	}
	
}


















