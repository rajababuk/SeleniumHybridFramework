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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;

import com.selenium.dto.TestCaseBean;
import com.selenium.dto.TestDataBean;
import com.selenium.dto.TestScenarioBean;


/**
 * This class is for XML parsing.  
 * All xml files under resources/TestData/*.xml will be parse from this class. 
 * There are three method defined in this class.  
 * 	 ParseTestScenarios()
 *		 ParseTestCases()
 *		 ParseTestData()
 *
 * All these methods are called from Global Model class before starting the execution.
 * XML file path read from configuration file though GlbalVar class. 
 * Test Scenarios , Test Cases, and Test data values are manipulated using 'List' collection interface with ArrayList framework. 
 * 
 * @author ashvini sharma
 * @version 1.0
 * 
 * Last Edited by ashvini sharma on @date 21-June-2015
 * @version 1.0
 *
 */

public class XMLparsing {
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(XMLparsing.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	 //  Global variables of this class. Getting xml file's path from configuration file
	String test_scenraios_XML_path = GlobalVar.testscenarios_xml;
	String test_case_XML_path = GlobalVar.testcase_xml;
	String test_data_XML_path = GlobalVar.testdata_xml;
	
	//Collection interface 
	List<TestScenarioBean> sclist = new ArrayList<TestScenarioBean>();
	List<TestCaseBean> tclist = new ArrayList<TestCaseBean>();
	List<TestDataBean> tdlist = new ArrayList<TestDataBean>();
	
	List<TestCaseBean> tclist1 = new ArrayList<TestCaseBean>();
    //Bean class object for all XML files.
    TestScenarioBean sc = null;
    TestCaseBean tc = null;
    TestDataBean td = null;
	
	//XML tag constants for test scenarios, test cases and test data
	static final String scenario="scenario";
	static final String sid="sid";
	static final String execute_flag="execute";
	static final String testcase_num="testcase";
	static final String testcase_stepid="teststepID";
	static final String testcase_record="record";

	/**
	 * Test Scenario XML parsing function.
	 * This method reads XML file using sTax parser. 
	 */
	public void ParseTestScenarios() {
		try {
		      // First, create a new XMLInputFactory
		      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		      
		      // Setup a new eventReader
		      InputStream in = new FileInputStream(test_scenraios_XML_path);
		      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

		      while (eventReader.hasNext()) {
		    	  XMLEvent event = eventReader.nextEvent();
		    	   	  
		    	  if (event.isStartElement()) {
		              StartElement startElement = event.asStartElement();
		              // If we have an release_info element, we create a new item
		              if (startElement.getName().getLocalPart().equals(scenario)) {
		            	  sc = new TestScenarioBean();
		            	  Attribute idAttr = startElement.getAttributeByName(new QName(sid));
	                       if(idAttr != null){
	                    	   sc.setSid(idAttr.getValue());
	                       } 
		              	}		              
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(execute_flag)) {
			                  event = eventReader.nextEvent();
			                  sc.setTestscen_exec(event.asCharacters().getData());
			                  continue;
			                }
			              }
		    	  	}  
		            // If we reach the end of an scenario element, we add it to the list
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart().equals(scenario)) {
		            	  sclist.add(sc);
		            	  //Debug: System.out.println(sclist);
		              }
		            }  
		      }
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		
		//Debug: For-each loop for object
		for(TestScenarioBean sc : sclist){
            System.out.println(sc.toString());
        }

	}
	
	//Test Cases based on Test Scenarios
	public void ParseTestCases() {
		try {
		      // First, create a new XMLInputFactory
		      XMLInputFactory inputFactory2 = XMLInputFactory.newInstance();
		      
		      // Setup a new eventReader
		      InputStream intc = new FileInputStream(test_case_XML_path);
		      XMLEventReader eventReader2 = inputFactory2.createXMLEventReader(intc);

		      int i=0;
		      while (eventReader2.hasNext()) {
		    	  XMLEvent event = eventReader2.nextEvent();
		    	   	  
		    	  if (event.isStartElement()) {
		              StartElement startElement = event.asStartElement();
		              // If we have an release_info element, we create a new item
		              if (startElement.getName().getLocalPart().equals(scenario)) {
		            	  tc = new TestCaseBean();
		            	  Attribute idAttr = startElement.getAttributeByName(new QName(sid));
	                       if(idAttr != null){
	                    	  tc.setSid(idAttr.getValue());
	                       } 
		              	} 
		              /* if (startElement.getName().getLocalPart().equals(testcase_record)) {
		            	  tc = new TestCaseBean();
		              	}       */
		              
		              /*if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(scenario)) {
			                	
			                	Attribute idAttr = startElement.getAttributeByName(new QName(sid));
			                	if(idAttr != null){
			                    	 tc.setSid(idAttr.getValue());
			                       } 
			                	continue;
			                }
			              }*/
		              
		              
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(testcase_num)) {
			                  event = eventReader2.nextEvent();
			                  tc.setTestcase_num(event.asCharacters().getData());
			                  continue;
			                }
			              }
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(execute_flag)) {
			                  event = eventReader2.nextEvent();
			                  tc.setTestcase_exec(event.asCharacters().getData());
			                  continue;
			                }
			              }
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(testcase_stepid)) {
			                  event = eventReader2.nextEvent();
			                  tc.setTestcase_stepid(event.asCharacters().getData());
			                  continue;
			                }
			              }
		    	  	}  
		            // If we reach the end of an scenario element, we add it to the list
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart().equals(scenario)) {
		            	  //tclist.add(tc);		      
		            	  //System.out.println(tclist);
		            	  
		              }
		              if (endElement.getName().getLocalPart().equals(testcase_record)) {
		            	  tclist.add(tc);
		            	  tc = new TestCaseBean();
		            	  //System.out.println(tclist);
		              }
		            } 
		      }
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		
		//Debug: For-each loop for object
		for(TestCaseBean tc : tclist){
          System.out.println(tc.toString());
		}
		//System.out.println(tclist);

	}
	
	//Test Data based on Test Cases
	public void ParseTestData() {
		
	}
	
	//End Class
}

