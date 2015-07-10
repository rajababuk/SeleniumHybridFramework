package com.selenium.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class DAOImplement implements DAOInterface{

	public String ExecQuery(String query) {
		//log.info("Step: Executing DB Query...");
		Statement sta = conn.createStatement();
		String Sql = query;
		ResultSet rs = sta.executeQuery(Sql);
		return rs.toString();
	}
	public boolean Insert() {
		
		return false;
	}
	
	
	
}
