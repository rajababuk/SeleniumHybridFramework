package com.selenium.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;
import com.selenium.dto.GlobalBean;

public class XMLparsing {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(XMLparsing.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	//Global variables
	String release_num = GlobalVar.release_num;
	String test_scenraios_XML_path = GlobalVar.testscenarios_xml;
	//debug: System.out.println("Release Number" + GlobalVar.release_num);
	
	//XML tag constants for test scenarios
	static final String scenario="scenario";
	static final String sid="sid";
	static final String execute_flag="execute";
	
	//Test Scenario XML parsing function
	public void ParseTestScenarios() {

		List<GlobalBean> sclists = new ArrayList<GlobalBean>();
		try {

		      // First, create a new XMLInputFactory
		      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		      
		      // Setup a new eventReader
		      InputStream in = new FileInputStream(test_scenraios_XML_path);
		      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		      
		      // read the XML document
		      GlobalBean sclist = null;
		      
		      while (eventReader.hasNext()) {
		    	  XMLEvent event = eventReader.nextEvent();
		    	   	  
		    	  if (event.isStartElement()) {
		              StartElement startElement = event.asStartElement();
		              // If we have an release_info element, we create a new item
		              if (startElement.getName().getLocalPart() == (scenario)) {
		            	  sclist = new GlobalBean();

		            	// We read the attributes from this tag and add the date
		                // attribute to our object
		                Iterator<Attribute> attributes = startElement.getAttributes();
		                while (attributes.hasNext()) {
		                  Attribute attribute = attributes.next();
		                  if (attribute.getName().toString().equals(sid)) {
		                	 sclist.setSid(attribute.getValue());
		                  }
		                }
		              }		              

		              if (event.isStartElement()) {
		                if (event.asStartElement().getName().getLocalPart().equals(execute_flag)) {
		                  event = eventReader.nextEvent();
		                  sclist.setExecute_flag(execute_flag);
		                  continue;
		                }
		              }
		              
		            // If we reach the end of an scenario element, we add it to the list
		            if (event.isEndElement()) {
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart() == (scenario)) {
		            	  sclists.add(sclist);
		              }
		            }  
		    	  }
		      }
		      
		      
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }

	    for (GlobalBean sclist : sclists) {
	        System.out.println(sclist);
	      }

	}
	
	
	
}

