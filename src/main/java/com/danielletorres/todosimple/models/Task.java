package com.danielletorres.todosimple.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
	public static final String TABLE_NAME = "task";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;
	
	@Column(name = "description", length = 255, nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 255)
	private String descritpion;
	
	public Task() {
		super();
	}

	public Task(Long id, User user, @NotNull @NotEmpty @Size(min = 1, max = 255) String descritpion) {
		super();
		this.id = id;
		this.user = user;
		this.descritpion = descritpion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descritpion, id, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(descritpion, other.descritpion) && Objects.equals(id, other.id)
				&& Objects.equals(user, other.user);
	}	
}