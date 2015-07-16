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

public class GlobalBean {
	
	private String rid;
	private String sid;
	private String execute;
	
	/**
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}
	/**
	 * @param rid the rid to set
	 */
	public void setRid(String rid) {
		this.rid = rid;
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
	/**
	 * @return the execute
	 */
	public String getExecute() {
		return execute;
	}
	/**
	 * @param execute the execute to set
	 */
	public void setExecute(String execute) {
		this.execute = execute;
	}

}


















