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

public class TestDataBean {
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
	/**
	 * @return the step_action
	 */
	public String getStep_action() {
		return step_action;
	}
	/**
	 * @param step_action the step_action to set
	 */
	public void setStep_action(String step_action) {
		this.step_action = step_action;
	}
	/**
	 * @return the locator_id
	 */
	public String getLocator_id() {
		return locator_id;
	}
	/**
	 * @param locator_id the locator_id to set
	 */
	public void setLocator_id(String locator_id) {
		this.locator_id = locator_id;
	}
	/**
	 * @return the locator_class
	 */
	public String getLocator_class() {
		return locator_class;
	}
	/**
	 * @param locator_class the locator_class to set
	 */
	public void setLocator_class(String locator_class) {
		this.locator_class = locator_class;
	}
	/**
	 * @return the locator_xpath
	 */
	public String getLocator_xpath() {
		return locator_xpath;
	}
	/**
	 * @param locator_xpath the locator_xpath to set
	 */
	public void setLocator_xpath(String locator_xpath) {
		this.locator_xpath = locator_xpath;
	}
	/**
	 * @return the step_data
	 */
	public String getStep_data() {
		return step_data;
	}
	/**
	 * @param step_data the step_data to set
	 */
	public void setStep_data(String step_data) {
		this.step_data = step_data;
	}
	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}
	/**
	 * @param sid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
	private String tid;
	private String testcase_stepid;
	private String step_action;
	private String locator_id;
	private String locator_class;
	private String locator_xpath;
	private String step_data;
	
	
    @Override
    public String toString() {
        return "Test Case ID " +  this.tid + "  Action = "+ this.step_action +" Locator-ID = " + this.locator_id + " Locator-class:  " + this.locator_class +
        		" Locator-xpath: "  + this.locator_xpath +  "  Test-Data: = "  + this.step_data;
    }
}
