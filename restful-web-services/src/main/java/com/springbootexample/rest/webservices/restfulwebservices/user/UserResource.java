package com.springbootexample.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public EntityModel<User> retriveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
			if(user == null)
				throw new UserNotFoundException("User not found with id :"+id);
			
			//Implementation for HATEOAS (Hypermedia as the Engine of Application State)
			
			EntityModel<User> entityModel = EntityModel.of(user);
			WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retrievAllUsers());
			entityModel.add(linkToUser.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid  @RequestBody User  user) {
		User savedUser = userDaoService.saveUser(user);
		
		URI location =  ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/user/{id}")
	public void deleteUserByID(@PathVariable int id) {
		User user = userDaoService.deleteUserByID(id);
			if(user == null)
				throw new UserNotFoundException("User not found with id :"+id);
	}
}
