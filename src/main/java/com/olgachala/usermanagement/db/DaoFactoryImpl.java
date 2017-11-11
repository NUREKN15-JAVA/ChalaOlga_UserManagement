package com.olgachala.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {
	
	public userDAO getUserDao() {		
		userDAO result = null;
		try {
			Class clazz = Class.forName(
					properties.getProperty(USER_DAO));
			result = (userDAO) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		}catch(Exception e) {
			throw new RuntimeException(e);
		}		
		return result;		
	}

}
