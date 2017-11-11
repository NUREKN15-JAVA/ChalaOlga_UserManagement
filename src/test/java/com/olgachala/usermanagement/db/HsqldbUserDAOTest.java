package com.olgachala.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;


import com.olgachala.usermanagement.User;
import com.olgachala.usermanagement.db.ConnectionFactory;
import com.olgachala.usermanagement.db.ConnectionFactoryImpl;
import com.olgachala.usermanagement.db.DatabaseExñeption;
import com.olgachala.usermanagement.db.HsqldbUserDao;

public class HsqldbUserDAOTest extends DatabaseTestCase {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	/**
	 * Constants FIND_USER_ID, UPDATE_USER_ID, DELETE_USER_ID contain 
	 * user id for testing methods from the class "com.olgachala.usermanagement.db.HsqldbUserDao"
	 */
	
	private static final Long FIND_USER_ID = 1000L;
	private static final Long UPDATE_USER_ID = 1001L;
	private static final Long DELETE_USER_ID = 1002L;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
		
	}
	
	/**
	 * Test method for "com.olgachala.usermanagement.db.HsqlUserDao.create(User user)"
	 */

	public void testCreate() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDate(new Date());
		assertNull(user.getId());
		try {
			User createUser = dao.create(user);
			createUser = dao.create(user);
			assertNotNull(createUser);
			assertNotNull(createUser.getId());
			assertEquals(user.getFullName(), user.getFullName());
			assertEquals(user.getAge(), createUser.getAge());
		} catch(DatabaseExñeption e){
			fail(e.toString());
		}		
	}
	
	public void testUpdate() {
		try {
			User user = dao.find(UPDATE_USER_ID);
			user.setFirstName("John");
			dao.update(user);
			User updatedUser = dao.find(UPDATE_USER_ID);
			assertNotNull("User was not updated", updatedUser);
			assertEquals("Difference between old and updated data of user", user.getFirstName(), updatedUser.getFirstName());
		} catch (DatabaseExñeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testFindById() {
		try {
			User findedUser = dao.find(FIND_USER_ID);
			assertNotNull("There is no such user", findedUser);
			assertEquals("Difference between user's id", FIND_USER_ID, findedUser.getId());
		} catch (DatabaseExñeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testDeleteUser() {
		User user = null;
		try {
			user = dao.find(DELETE_USER_ID);
			dao.delete(user);
			User deletedUser = dao.find(DELETE_USER_ID);
			assertNotNull("User was not deleted", deletedUser);
		} catch (DatabaseExñeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testFindAll()
	{
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size", 3, collection.size());
		} catch (DatabaseExñeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	 @Override
	    protected IDatabaseConnection getConnection() throws Exception {
	        connectionFactory = new ConnectionFactoryImpl(
	                "org.hsqldb.jdbcDriver",
	                "jdbc:hsqldb:file:db/usermanagement",
	                "sa",
	                "");
	        return new DatabaseConnection(connectionFactory.createConnection());
	    }

	    @Override
	    protected IDataSet getDataSet() throws Exception {
	        return new XmlDataSet(
	                getClass().getClassLoader().getResourceAsStream("userDataSet.xml"));
	    }
	}