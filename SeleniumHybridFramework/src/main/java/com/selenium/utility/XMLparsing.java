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
 * All these methods are called by Global Model class before starting the execution of Global Model Class.
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
	static final String teststep_tid="tid";
	static final String testdata_step="step";
	static final String testdata_action="action";
	static final String locator_id="locator-id";
	static final String locator_class="locator-class";
	static final String locator_xpath="locator-xpath";
	static final String testdata_data = "data"; 
	
	/**
	 * Test Scenario XML parsing function.
	 * This method returns all <scenario> elements from XML regardless the release id.
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
		/* for(TestScenarioBean sc : sclist){
            System.out.println(sc.toString());
        } */
	} // End ParseTestScenarios()
	
	/**
	 * Test Case XML parsing method.
	 * This method returns all <record> elements from XML regardless the scenario id.
	 */
	public void ParseTestCases() {
		try {
		      // First, create a new XMLInputFactory
		      XMLInputFactory inputFactory2 = XMLInputFactory.newInstance();
		      
		      // Setup a new eventReader
		      InputStream intc = new FileInputStream(test_case_XML_path);
		      XMLEventReader eventReader2 = inputFactory2.createXMLEventReader(intc);

		      //Initializer a global variable
		      String SecnarioID=null;
		      
		      while (eventReader2.hasNext()) {
		    	  XMLEvent event = eventReader2.nextEvent();
		    	  
		    	  //Fetch first element from XML
		    	  if (event.isStartElement()) {
		              StartElement startElement = event.asStartElement();
		              if (startElement.getName().getLocalPart().equals(scenario)) {
		            	  //Creating a object 'tc' with <scenario> occurrence. 
		            	  //This will be created once until next set of <record>
		            	  tc = new TestCaseBean();
		            	  //We have created object to get 'sid' attribute value.
		            	  Attribute idAttr = startElement.getAttributeByName(new QName(sid));
	                       if(idAttr != null){
	                    	  tc.setSid(idAttr.getValue());
	                    	  //Store in 'sid' value in a global variable.
	                    	  SecnarioID = idAttr.getValue();
	                       } 
		              	} 
		              //'sid' values needs to be associated with each <record> set.
		              //Above 'sid' value get associated with first <record> i.e. with record immediate after <scenario>
		              //To associate 'sid' value with other <record> set, using specific 'setter' with tc object.
		              if (startElement.getName().getLocalPart().equals(testcase_record)) {
		            	  //It use 'tc' object to set value for 'sid' in setter.
		            	  //First time 'tc' object will be with <scenario> and second time 'tc' object will be with <record> occurrence. 
		            	  tc.setSid(SecnarioID);
		              	}       
		              
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
		            // Once getting end element </xxxx> of XMLS. Check if its </record>.
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart().equals(testcase_record)) {
		            	  //First time, it will add object which was created with <scenario> occurrence. 
		            	  //So adding in list so far received elements in bean setter. Next time, it will add object created in this block.
		            	  tclist.add(tc);
		            	  //In this block a new object will be created. First was created with <scenario> and second with <record> occurrence.
		            	  //Creating a object 'tc' with <record> occurrence.
		            	  tc = new TestCaseBean();
		              }
		            } 
		      }
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		
		//Debug: For-each loop for object
		/*for(TestCaseBean tc : tclist){
          System.out.println(tc.toString());
		} */
	} //End ParseTestCases ()
	
	/**
	 * Test Data XML parsing method.
	 * This method returns all <steps> elements from XML regardless the test step id.
	 */
	public void ParseTestData() {
		try {
		      // First, create a new XMLInputFactory
		      XMLInputFactory inputFactory3 = XMLInputFactory.newInstance();
		      
		      // Setup a new eventReader
		      InputStream intc = new FileInputStream(test_data_XML_path);
		      XMLEventReader eventReader3 = inputFactory3.createXMLEventReader(intc);

		      //Initializer a global variable
		      String TestID=null;
		      
		      while (eventReader3.hasNext()) {
		    	  XMLEvent event = eventReader3.nextEvent();
		    	  
		    	  //Fetch first element from XML
		    	  if (event.isStartElement()) {
		              StartElement startElement = event.asStartElement();
		              if (startElement.getName().getLocalPart().equals(testcase_stepid)) {
		            	  //Creating a object 'td' with <teststepID> occurrence. 
		            	  //This will be created once until next set of <record>
		            	  td = new TestDataBean();
		            	  //We have created object to get 'tid' attribute value.
		            	  Attribute idAttr = startElement.getAttributeByName(new QName(teststep_tid));
	                       if(idAttr != null){
	                    	  td.setTid(idAttr.getValue());
	                    	  //Store in 'tid' value in a global variable.
	                    	  TestID = idAttr.getValue();
	                       } 
		              	} 
		              //'tid' values needs to be associated with each <step> set.
		              //Above 'tid' value get associated with first <step> i.e. with record immediate after <teststepID>
		              //To associate 'tid' value with other <step> set, using specific 'setter' with tc object.
		              if (startElement.getName().getLocalPart().equals(testdata_step)) {
		            	  //It use 'td' object to set value for 'tid' in setter.
		            	  //First time 'td' object will be with <teststepID> and second time 'td' object will be with <step> occurrence. 
		            	  td.setTid(TestID);
		              	}       
		              
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(testdata_action)) {
			                	event = eventReader3.nextEvent();
			                    if(event.isEndElement()) {         	
			                    }
			                    else {
			                    	td.setStep_action(event.asCharacters().getData());
			                    }
			                  continue;
			                }
			              }
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(locator_id)) {
			                	event = eventReader3.nextEvent();
			                	if(event.isEndElement()) {       	
			                    }
			                    else {
			                	td.setLocator_id(event.asCharacters().getData()); 
			                    }
			                	continue;
			                }
			              }	
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(locator_class)) {
			                  event = eventReader3.nextEvent();
			                  if(event.isEndElement()) {    	
			                    }
			                    else {
			                	  td.setLocator_class(event.asCharacters().getData());
			                  }
			                  continue;
			                }
			              }
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(locator_xpath)) {
			                  event = eventReader3.nextEvent();
			                  if(event.isEndElement()) {  	
			                    }
			                    else {
			                	  td.setLocator_xpath(event.asCharacters().getData());
			                  }
			                  continue;
			                }
			              }
		              if (event.isStartElement()) {
			                if (event.asStartElement().getName().getLocalPart().equals(testdata_data)) {
			                  event = eventReader3.nextEvent();
			                  if(event.isEndElement()) {      	
			                    }
			                    else {
			                	  td.setStep_data(event.asCharacters().getData());
			                  }
			                  continue;
			                }
			              }
		    	  	}  
		            // Once getting end element </xxxx> of XMLS. Check if its </step>.
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart().equals(testdata_step)) {
		            	  //First time, it will add object which was created with <teststepID> occurrence. 
		            	  //So adding in list so far received elements in bean setter. Next time, it will add object created in this block.
		            	  tdlist.add(td);
		            	  //In this block a new object will be created. First was created with <scenario> and second with <step> occurrence.
		            	  //Creating a object 'tc' with <step> occurrence.
		            	  td = new TestDataBean();
		              }
		            } 
		      }
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		
		//Debug: For-each loop for object
		/* for(TestDataBean td : tdlist){
        System.out.println(td.toString());
		} */
		
	} // End ParseTestData()
	
	
	public void FilterTestCase() {
		for(TestScenarioBean sc : sclist){
            System.out.println(sc.getTestscen_exec());
			//System.out.println(sc.toString());
        }
		
		
	}
	
	//End Class
}

