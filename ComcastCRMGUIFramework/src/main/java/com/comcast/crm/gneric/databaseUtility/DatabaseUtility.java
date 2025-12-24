package com.comcast.crm.gneric.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {

	Connection conn;
	
	public void getDBConnection(String url, String username, String password) throws Exception {
		try {
			Driver driver = new Driver();
			
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDBConnection(String username, String password) {
		try {
			Driver driver = new Driver();
			
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company",username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closeDBConnection() throws Exception {
		conn.close();
	}
	
	public ResultSet executeSelectQuery(String query) throws Exception {
		ResultSet result = null;
		try {
			Statement stmt = conn.createStatement();
			 result = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
