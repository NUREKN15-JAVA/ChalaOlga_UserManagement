package com.olgachala.usermanagement.db;

import java.util.Collection;

import com.olgachala.usermanagement.User;

/**
 * interface for user class which implement DAO pattern with all CRUD opps
 * @author Olha 
 *
 */
public interface userDAO {
	/**
	 * Add user into DB users table and get new user's id from DB
	 * @param user all fields of user must be non-null except of id field
	 * @return copy user from db with id auto-created
	 * @throws DatabaseExeption in case of any error with db
	 */
	User create(User user) throws DatabaseExñeption;
	
	/**
	 * Find user into DB users table by user`s id and get all information about users from db
	 * @param id is used for searching of users in DB users table
	 * @return all information about users from db
	 * @throws DatabaseException in case of any error with db
	 */
	
	User find(Long id) throws DatabaseExñeption;
	

	/**
	 * Update all information about users into DB users table by user`s id	 
	 * @param user all field of user must be non-null except of id field
	 * @throws DatabaseException in case of any error with db
	 */
	
	void update(User user) throws DatabaseExñeption;
	
	/**
	 * Delete all information about users into DB users table by user`s id	 
	 * @param user all field of user must be non-null except of id field
	 * @throws DatabaseException in case of any error with db
	 */
	
	void delete(User user) throws DatabaseExñeption;
	
	/**
	 * Find information about all users into DB users table  
	 * @param a collection of all users from db
	 * @throws DatabaseException in case of any error with db
	 */
	
	Collection<User> findAll() throws DatabaseExñeption;
	
	/**
     * Set the connection factory.
     * @param connectionFactory is used for connection factory
     */

	void setConnectionFactory(ConnectionFactory connectionFactory);
}
