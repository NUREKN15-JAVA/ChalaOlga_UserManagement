package com.olgachala.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class TestUser extends TestCase {
	/*
	 * actual data about age for 2017 
	 */
	private static final int AGE = 19;
	private static final int yearOfBith = 1998;
	private static final int dayOfBirth = 15;
	private User user;
	private Date date;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(yearOfBith, Calendar.AUGUST, dayOfBirth);
	    date = calendar.getTime();
	}
	public void testGetFullName(){
		user.setFirstName("Ola");
		user.setLastName("Chala");
		assertEquals("Chala, Olya", user.getFullName());
		try {
	            user.getFullName();
	    } catch (IllegalStateException e) {
	    	 assertEquals(e.getMessage(), "First name or last name equal null");
	         }
		}	
	
	public void testGetAge() {
		user.setDate(date);
		assertEquals(AGE, user.getAge());
	}

}
