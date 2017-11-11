package com.olgachala.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.olgachala.usermanagement.User;


public class MockUserDao implements userDAO {

		private long id = 0;
		private Map<Long, User> users = new HashMap<>();
		
		
		public User create(User user) throws DatabaseExñeption {
			Long currentId = new Long(++id);
			user.setId(currentId);
			users.put(currentId, user);
			return user;
		}

		
		public User find(Long id) throws DatabaseExñeption {
			return (User) users.get(id);
		}

		
		public void update(User user) throws DatabaseExñeption {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);

		}

		
		public void delete(User user) throws DatabaseExñeption {
			Long currentId = user.getId();
			users.remove(currentId);

		}


		public Collection<User> findAll() throws DatabaseExñeption {
			return users.values();
		}

		
		public void setConnectionFactory(ConnectionFactory connectionFactory) {
		}
		
}
