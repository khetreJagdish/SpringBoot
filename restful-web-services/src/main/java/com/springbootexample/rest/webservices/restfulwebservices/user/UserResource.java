package com.springbootexample.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User  user) {
		User savedUser = userDaoService.saveUser(user);
		
		URI location =  ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
