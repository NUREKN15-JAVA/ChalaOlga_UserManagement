package com.olgachala.usermanagement.db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {
	
	/*
	 * test method for "com.olgachala.usermanagement.db.getUserDao()
	 */
public void testGetUserDao() {
		
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null",daoFactory);
			userDAO userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instance is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
