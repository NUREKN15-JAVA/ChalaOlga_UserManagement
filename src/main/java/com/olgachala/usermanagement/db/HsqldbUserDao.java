package com.olgachala.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;


import org.hsqldb.Result;

import com.olgachala.usermanagement.User;

public class HsqldbUserDao implements userDAO {
	
	private static final String DELETE_USERS_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String SELECT_USERS_BY_ID = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofBirth) VALUES (?, ?, ?) ";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDao() {

	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseExñeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDate().getTime()));
			int n= statement.executeUpdate();
			if(n!=1) {
				throw new DatabaseExñeption("Number of the inserted rows:" +n);
			}
			CallableStatement callableStatement = connection
					.prepareCall("call IDENTITY");
			ResultSet keys = callableStatement.executeQuery();
			User insertedUser = new User(user);
			if (keys.next()) {
				insertedUser.setId(keys.getLong(1));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return insertedUser;
		}catch(DatabaseExñeption e) {
			throw e;
		}catch(SQLException e){
			throw new  DatabaseExñeption(e);
		}
		
		
	}

	@Override
	public User find(Long id) throws DatabaseExñeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			User user = new User();
			while (rs.next()) {
				user.setId(rs.getLong(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setDate(rs.getDate(4));

			}
			rs.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseExñeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExñeption(e);
		}

	}

	@Override
	public void update(User user) throws DatabaseExñeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDate().getTime()));
			statement.setLong(4, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseExñeption("Number of inserted rows" + n);
			}
			connection.close();
			statement.close();
		} catch (DatabaseExñeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExñeption(e);
		}

	}

	@Override
	public void delete(User user) throws DatabaseExñeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USERS_QUERY);
			statement.setLong(1, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseExñeption("Number of inserted rows" + n);
			}
			connection.close();
			statement.close();
		} catch (DatabaseExñeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExñeption(e);
		}

	}

	@Override
	public Collection<User> findAll() throws DatabaseExñeption {
		Collection result= new LinkedList();
		
		try {
			Connection connection=connectionFactory.createConnection();
			connection.createStatement();
			Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
	        User user = new User();
	        while (resultSet.next()) {
	        		user.setId(resultSet.getLong(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setDate(resultSet.getDate(4));
					result.add(user);				       	
            }
            
		} catch (DatabaseExñeption e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseExñeption(e);
        } 
		return result;
	}
	

}
