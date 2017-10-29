package com.olgachala.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String user;
	private String url;
	private String driver;
	private String password;
	
	
	public ConnectionFactoryImpl(String driver, String url, String user,  String password)
	{
		this.driver= driver;
		this.url = url;
		this.user = user;
		this.password =  password;		
	}
	
	public Connection createConnection() throws DatabaseExñeption{
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			throw new RuntimeException (e);
		}
		
		try {
			return DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			throw new DatabaseExñeption(e);
		}
		
		
		
	}
	
}
