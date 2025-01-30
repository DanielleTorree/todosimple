package com.danielletorres.todosimple.exceptions;

public class ToDoException extends RuntimeException {
	
	private final transient Error error;

	public ToDoException(Error error) {
		this.error = error;
	}

	public ToDoException(String errorMessage) {
		super(errorMessage);
		this.error = Error.of(errorMessage);
	}
	
	public ToDoException(Integer errorCode, String errorMessage) {
		super(errorMessage);
		this.error = Error.of(errorCode, errorMessage);
	}

	public Error getError() {
		return error;
	}	
}