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
 * Last Edited by ashvini sharma on @date 21-June-2015
 * @version 1.0
 *
 */

public class TestCaseBean {
	
	private String testcase_num;
	private String testcase_exec;
	private String testcase_stepid;
	private String sid;
	
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
	
	/**
	 * @return the testcase_num
	 */
	public String getTestcase_num() {
		return testcase_num;
	}
	/**
	 * @param testcase_num the testcase_num to set
	 */
	public void setTestcase_num(String testcase_num) {
		this.testcase_num = testcase_num;
	}
	/**
	 * @return the testcase_exec
	 */
	public String getTestcase_exec() {
		return testcase_exec;
	}
	/**
	 * @param testcase_exec the testcase_exec to set
	 */
	public void setTestcase_exec(String testcase_exec) {
		this.testcase_exec = testcase_exec;
	}
	/**
	 * @return the testcase_stepid
	 */
	public String getTestcase_stepid() {
		return testcase_stepid;
	}
	/**
	 * @param testcase_stepid the testcase_stepid to set
	 */
	public void setTestcase_stepid(String testcase_stepid) {
		this.testcase_stepid = testcase_stepid;
	}

    @Override
    public String toString() {
        return "Test Scenario  " +  this.sid + "  Case:: ID="+ this.testcase_num +" Execution Flag = " + this.testcase_exec + " Step ID:  " + this.testcase_stepid;
    }
}
