package com.olgachala.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {

	Connection createConnection() throws DatabaseEx�eption;
	

}
