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

import com.selenium.dto.TestScenarioBean;

public class XMLparsing {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(XMLparsing.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	
	 /*  Global variables */
	//Getting xml file's path from configuration file
	String test_scenraios_XML_path = GlobalVar.testscenarios_xml;
	String test_case_XML_path = GlobalVar.testcase_xml;
	String test_data_XML_path = GlobalVar.testdata_xml;
	
	//Collection interface 
	List<TestScenarioBean> sclist = new ArrayList<TestScenarioBean>();
    
    //Bean class object for test scenarios
    TestScenarioBean sc = null;
	
	//XML tag constants for test scenarios
	static final String scenario="scenario";
	static final String sid="sid";
	static final String execute_flag="execute";
	
	//Test Scenario XML parsing function
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
		                  sc.setExecute_flag(event.asCharacters().getData());
		                  continue;
		                }
		              }
		    	  }  
		            // If we reach the end of an scenario element, we add it to the list
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart().equals(scenario)) {
		            	  sclist.add(sc);
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
		
	}
	
	//Test Data based on Test Cases
	public void ParseTestData() {
		
	}
	
	//End Class
}

