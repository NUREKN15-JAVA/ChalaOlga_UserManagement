package com.olgachala.usermanagement.db;

public class DatabaseEx�eption extends Exception {
 public DatabaseEx�eption (Exception e) {
	 super(e);
 }
 
public DatabaseEx�eption(String string) {
	super(string);
	}
}
