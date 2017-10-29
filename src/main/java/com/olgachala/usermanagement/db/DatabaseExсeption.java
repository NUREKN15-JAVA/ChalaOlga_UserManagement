package com.olgachala.usermanagement.db;

public class DatabaseExñeption extends Exception {
 public DatabaseExñeption (Exception e) {
	 super(e);
 }
 
public DatabaseExñeption(String string) {
	super(string);
	}
}
