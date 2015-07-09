package com.selenium.dbconfiguration;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

import org.apache.log4j.Logger;

import com.selenium.dto.GlobalBean;

public class DBconnection {
	
	//Logging details based on log4j.property file 
	static Logger log = Logger.getLogger(DBconnection.class.getName());
	static final String LOG_PROPERTIES_FILE = "resources/log4j.properties";

	public Connection DBconnectionStr(String DBName) throws ClassNotFoundException, SQLException {
		
		//Reading configuration file to get Test Scenarios from Input XL file
		//log.info("Step: Reading DB Connection details");
		//log.info("Call to Read configuration file...");
		
		//Bean class object for Setter.
		GlobalBean GetSet= new GlobalBean();
		String DBConnection=GetSet.getDBConnectionString();
		String DBUser=GetSet.getUser_id();
		String DBPass=GetSet.getPassword();
		
		//DBconnection variable
		Connection DBconnection = null;
		Statement statement = null;
		
	    try {
	    	DBconnection = getConnection();
	        // Do work with connection
	    	statement = connection.createStatement();
	        String selectEmployeesSQL = "SELECT * FROM employees";
	        resultSet = statement.executeQuery(selectEmployeesSQL);

	        while (resultSet.next()) {
	          printEmployee(resultSet);
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	        if (resultSet != null) {
	          try {
	            resultSet.close();
	          } catch (SQLException e) {
	          } // nothing we can do
	        }
	        if (statement != null) {
	          try {
	            statement.close();
	          } catch (SQLException e) {
	          } // nothing we can do
	        }
	        if (connection != null) {
	          try {
	            connection.close();
	          } catch (SQLException e) {
	          } // nothing we can do
	        }
	      }
		String connection = ("jdbc:sqlserver://" + DBConnection +";user=" + DBUser + ";password=" + DBPassword + ";database=" + DBName);
		Connection conn = DriverManager.getConnection(connection);	
		return conn;
	}
	
	  private static Connection getConnection() throws NamingException, SQLException {
		    InitialContext initCtx = createContext();
		    String jndiName = "HrDS";
		    ConnectionPoolDataSource dataSource = (ConnectionPoolDataSource) initCtx.lookup(jndiName);
		    PooledConnection pooledConnection = dataSource.getPooledConnection();
		    return pooledConnection.getConnection(); // Obtain connection from pool
		  }
	
}
