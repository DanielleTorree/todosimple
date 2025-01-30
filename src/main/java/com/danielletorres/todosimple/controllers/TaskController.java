package com.danielletorres.todosimple.controllers;

import java.net.URI;
import java.util.List;

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

import com.danielletorres.todosimple.models.Task;
import com.danielletorres.todosimple.services.TaskService;
import com.danielletorres.todosimple.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable("id") Long id) {
		Task obj = this.taskService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Task>> findAllByUserId(@PathVariable("id") Long userId) {
		this.userService.findById(userId);
		
		List<Task> tasks = this.taskService.findAllByUserId(userId);
		
		return ResponseEntity.ok().body(tasks);
	}
	
	@PostMapping
	@Validated
	public ResponseEntity<Void> create(@Valid @RequestBody Task task) {
		this.taskService.create(task);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(task.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Validated
	public ResponseEntity<Void> update(@Valid @RequestBody Task task, @PathVariable("id") Long id) {
		task.setId(id);		
		this.taskService.update(task);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.taskService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
