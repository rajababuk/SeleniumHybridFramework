package com.selenium.utility;

import org.apache.log4j.Logger;

public class XMLparsing {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(XMLparsing.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	public void xmlTestData() {
		System.out.println("Release Number" + GlobalVar.release_num);
	}
	
}

