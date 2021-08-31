package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserRepository repository;
	
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/users")
	CollectionModel<EntityModel<User>> all() {
		List<EntityModel<User>> users = repository.findAll().stream().map(user -> EntityModel.of(user,
				linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).all()).withRel("users")
				)).collect(Collectors.toList());
		
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}
	
	@GetMapping("/users/{id}")
	EntityModel<User> one(@PathVariable Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		
		return EntityModel.of(user, 
				linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
				linkTo(methodOn(UserController.class).all()).withRel("users")
				);
	}
	
	@PutMapping("/users/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		
		return repository.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					user.setPassword(newUser.getPassword());
					user.setSpots(newUser.getSpots());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});
	}
	
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
