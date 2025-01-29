package com.danielletorres.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielletorres.todosimple.exceptions.ToDoException;
import com.danielletorres.todosimple.models.User;
import com.danielletorres.todosimple.repositories.TaskRepository;
import com.danielletorres.todosimple.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		
		return user.orElseThrow(() -> new ToDoException(
				"Usuário não encontrado. Id: " + id + ", Tipo: " + User.class.getName()
		));
	}
	
	@Transactional
	public User create(User user) {
		user.setId(null);
		
		User obj = this.userRepository.save(user);		
		// this.taskRepository.saveAll(obj.getTasks());
		
		return obj;
	}
	
	@Transactional
	public User uptate(User user) {
		User newObj = findById(user.getId());
		newObj.setPassword(user.getPassword());
		
		return this.userRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);

		try {
			this.userRepository.deleteById(id);
		} catch(Exception e) {
			throw new ToDoException("Não é possível exclui, pois há entidades relacionadas.");
		}
		
	}
}