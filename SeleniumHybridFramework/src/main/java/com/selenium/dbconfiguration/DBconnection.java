package com.selenium.dbconfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.selenium.dto.GlobalBean;
import com.selenium.utility.GlobalVar;

public class DBconnection implements Runnable  {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(DBconnection.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
	
	//Bean class object for Setter.
	String DBConnection=GlobalVar.DBConnectionString;
	String DBUser=GlobalVar.user_id;
	String DBPass=GlobalVar.password;
	
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
        for(int cnt=0; cnt<initialConnectionCount; cnt++)   
        {   
            // Add a new connection to the available list.   
            availableConnections.addElement(getConnection());   
        }   
           
        // Create the cleanup thread   
        cleanupThread = new Thread(this);   
        cleanupThread.start();   
    }    
	
	
    private Connection getConnection() throws SQLException   
    {   
        return DriverManager.getConnection(DBConnection, DBUser, DBPass);   
    }   
       
    public synchronized Connection checkout() throws SQLException   
    {   
        Connection newConnxn = null;   
           
        if(availableConnections.size() == 0)   
        {   
            // Im out of connections. Create one more.   
             newConnxn = getConnection();   
            // Add this connection to the "Used" list.   
             usedConnections.addElement(newConnxn);   
            // We dont have to do anything else since this is   
            // a new connection.   
        }   
        else   
        {   
            // Connections exist !   
            // Get a connection object   
            newConnxn = (Connection)availableConnections.lastElement();   
            // Remove it from the available list.   
            availableConnections.removeElement(newConnxn);   
            // Add it to the used list.   
            usedConnections.addElement(newConnxn);               
        }           
           
        // Either way, we should have a connection object now.   
        return newConnxn;   
    }   
      
    
    public synchronized void checkin(Connection c)   
    {   
        if(c != null)   
        {   
            // Remove from used list.   
            usedConnections.removeElement(c);   
            // Add to the available list   
            availableConnections.addElement(c);           
        }   
    }         
    
    
    public int availableCount()   
    {   
        return availableConnections.size();   
    }   
       
    
	public void run() {
        try   
        {   
            while(true)   
            {   
                synchronized(this)   
                {   
                    while(availableConnections.size() > initialConnectionCount)   
                    {   
                        // Clean up extra available connections.   
                        Connection c = (Connection)availableConnections.lastElement();   
                        availableConnections.removeElement(c);   
                           
                        // Close the connection to the database.   
                        c.close();   
                    }   
                       
                    // Clean up is done   
                }   
                   
                System.out.println("CLEANUP : Available Connections : " + availableCount());   
                   
                // Now sleep for 1 minute   
                Thread.sleep(60000 * 1);   
            }       
        }   
        catch(SQLException sqle)   
        {   
            sqle.printStackTrace();   
        }   
        catch(Exception e)   
        {   
            e.printStackTrace();   
        }   
		
	}

	
}
