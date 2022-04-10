package com.springbootexample.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> retrievAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User retriveUser(@PathVariable int id) {
		return userDaoService.findOne(id);
	}
}
