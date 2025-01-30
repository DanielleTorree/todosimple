package com.danielletorres.todosimple.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danielletorres.todosimple.models.User;
import com.danielletorres.todosimple.models.User.CreateUser;
import com.danielletorres.todosimple.models.User.UpdateUser;
import com.danielletorres.todosimple.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		User obj = this.userService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Validated(CreateUser.class)
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		User obj = this.userService.create(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Validated(UpdateUser.class)
	public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable("id") Long id) { 
		user.setId(id);		
		this.userService.uptate(user);

		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.userService.delete(id);
	
		return ResponseEntity.noContent().build();
	}
}
