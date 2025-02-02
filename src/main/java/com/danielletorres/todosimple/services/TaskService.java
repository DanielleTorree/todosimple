package com.danielletorres.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielletorres.todosimple.exceptions.ToDoException;
import com.danielletorres.todosimple.models.Task;
import com.danielletorres.todosimple.models.User;
import com.danielletorres.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	public Task findById(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		
		return task.orElseThrow(() -> new ToDoException(
				"Tarefa não encontrada."
		));
	}
	
	@Transactional
	public Task create(Task task) {
		User user = this.userService.findById(task.getUser().getId());
		task.setId(null);
		task.setUser(user);
		task = this.taskRepository.save(task); 
		return task;
	}
	
	@Transactional
	public Task update(Task task) {
		Task newObj = findById(task.getId());
		newObj.setDescritpion(task.getDescritpion());
		
		return this.taskRepository.save(newObj);
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		
		try {
			this.taskRepository.deleteById(id);
		} catch(Exception e) {
			throw new ToDoException("Não é possível excluir, pois há entidades relacionadas.");
		}
	}
	
}
