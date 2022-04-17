package com.springbootexample.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrievAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/user/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
			if(!user.isPresent())
				throw new UserNotFoundException("User not found with id :"+id);
			
			//Implementation for HATEOAS (Hypermedia as the Engine of Application State)
			
			EntityModel<User> resource = EntityModel.of(user.get());
			WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retrievAllUsers());
			resource.add(linkToUser.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid  @RequestBody User  user) {
		User savedUser = userRepository.save(user);
		
		URI location =  ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/jpa/user/{id}")
	public void deleteUserByID(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievAllUsers(@PathVariable int id){
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User not found with id :"+id);
		
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/jpa/user/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User not found with id :"+id);
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
				
		URI location =  ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
