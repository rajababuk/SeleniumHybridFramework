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

import com.selenium.dto.GlobalBean;

public class XMLparsing {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(XMLparsing.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	//Global variables
	//String release_num = GlobalVar.release_num;
	String test_scenraios_XML_path = GlobalVar.testscenarios_xml;
	List<GlobalBean> sclists = new ArrayList<GlobalBean>();
    
    // read the XML document
    GlobalBean sc = null;
	
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
		            	  sc = new GlobalBean();
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
		            	  sclists.add(sc);
		              }
		            }  
		      }
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		
		//Data in List variable: sclists
		//Debug: 
		for(GlobalBean sc : sclists){
            System.out.println(sc.toString());
        }

	}
}

