package com.olgachala.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String user;
	private String url;
	private String driver;
	private String password;
	
	
	public ConnectionFactoryImpl (String driver, String url, String user,  String password)
	{	
		this.driver= driver;
		this.url = url;
		this.user = user;
		this.password =  password;	
	}
	
	public ConnectionFactoryImpl (Properties properties) {
		this.driver = properties.getProperty("connetion.driver");
		this.url = properties.getProperty("connetion.url");
		this.user = properties.getProperty("connetion.user");
		this.password =  properties.getProperty("connetion.password");		
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
