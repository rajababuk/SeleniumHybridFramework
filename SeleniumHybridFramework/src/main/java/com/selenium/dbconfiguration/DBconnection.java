package com.selenium.dbconfiguration;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

import org.apache.log4j.Logger;

import com.selenium.dto.GlobalBean;

public class DBconnection implements Runnable  {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(DBconnection.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	//Bean class object for Setter.
	GlobalBean GetSet= new GlobalBean();
	String DBConnection=GetSet.getDBConnectionString();
	String DBUser=GetSet.getUser_id();
	String DBPass=GetSet.getPassword();
	
	//Number of initial connections
	private int initialConnectionCount = 5;  
	
	//Available connections to use
	private Vector availableConnections = new Vector(); 
	
	//Currently connections in use
	private Vector usedConnections = new Vector();  
	
	// The cleanup thread 
	private Thread cleanupThread = null;
	
    //Constructor   
    public DBconnection () throws SQLException   
    {   
        // Initialize the required parameters   
    	String urlString = DBConnection;   
        String userName = DBUser;   
        String password = DBPass;   
  
        for(int cnt=0; cnt<initialConnectionCount; cnt++)   
        {   
            // Add a new connection to the available list.   
            availableConnections.addElement(getConnection());   
        }   
           
        // Create the cleanup thread   
        cleanupThread = new Thread(this);   
        cleanupThread.start();   
    }    
	
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
