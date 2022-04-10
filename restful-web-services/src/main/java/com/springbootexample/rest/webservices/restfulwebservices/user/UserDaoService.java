package com.springbootexample.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	public static int userCount = 3;
	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Ryan", new Date()));
		users.add(new User(1, "Robert", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int userid) {
		for(User user : users) {
			if(user.getId() == userid) {
				return user;
			}
		}
		return null;
	}
}
