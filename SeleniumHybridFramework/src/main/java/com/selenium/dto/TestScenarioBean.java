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
 * This class is used to read & place getter-setter for variables used during the execution.   
 * 
 * @author ashvini sharma
 * @version 1.0
 * 
 * Last Edited by ashvini sharma on @date 16-June-2015
 * @version 1.0
 *
 */

public class TestScenarioBean {
	
	private String sid;
	private String testscen_exec;
	
	/**
	 * @return the testscen_exec
	 */
	public String getTestscen_exec() {
		return testscen_exec;
	}
	/**
	 * @param testscen_exec the testscen_exec to set
	 */
	public void setTestscen_exec(String testscen_exec) {
		this.testscen_exec = testscen_exec;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

    @Override
    public String toString() {
        return "Test Scenarios:: ID= "+ this.sid +" Execution Flag = " + this.testscen_exec;
    }
	

}


















