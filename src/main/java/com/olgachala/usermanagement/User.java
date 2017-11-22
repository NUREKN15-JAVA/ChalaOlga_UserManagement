package com.olgachala.usermanagement;

import java.util.Calendar;
import java.util.Date;


public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date date;
	public User(User user) {
		id = user.getId();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		date = user.getDate();
	}
	
	public User() {
		
	}
	
	public User (Long id, String firstName, String lastName, Date date) {
		this.id= id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
	}
	
	public User(String firstName2, String lastName2, Date date2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFullName(){
		if (lastName == null || firstName == null) {
            throw new IllegalStateException("First name or last name equal null");
        }
		return getLastName() +", "+  getFirstName();
	}
	public long getAge() {
		Calendar calendar = Calendar.getInstance();
		long currentYear = calendar.get(Calendar.YEAR);
		long currDayOfTheYear = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(getDate());
		long yearOfBirth = calendar.get(Calendar.YEAR);
		long birthDayOfTheYear = calendar.get(Calendar.DAY_OF_YEAR);
		
		long userAge = currentYear-yearOfBirth;
		if(currDayOfTheYear < birthDayOfTheYear) {
			userAge -= 1;
		}
		return userAge;		 
	}



public boolean equals(Object obj) {

		if (obj == null) {
		return false;
		}
		if (this == obj) {
		return true;
		}
		if (this.getId() == null && ((User) obj).getId() == null) {
		return true;
		}
		return this.getId().equals(((User) obj).getId());
		}

public int hashCode() {
	    if (this.getId()==null) {
	      return 0;
	    }
	    return this.getId().hashCode();
	  }
}