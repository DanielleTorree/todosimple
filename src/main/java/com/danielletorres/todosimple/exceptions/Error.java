package com.danielletorres.todosimple.exceptions;

import org.springframework.http.HttpStatus;

public class Error {
	
	private final Integer errorCode;
    private final String errorMessage;
    private final Long timestamp;

    private Error(Integer errorCode, String errorMessage, Long timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public static Error of(String errorMessage) {
        return new Error(HttpStatus.BAD_REQUEST.value(), errorMessage, System.currentTimeMillis());
    }

    public static Error of(Integer errorCode, String errorMessage) {
        return new Error(errorCode, errorMessage, System.currentTimeMillis());
    }	   
}