package com.school.exception;

public class InvalidProgramTypeException extends Exception {
    
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidProgramTypeException(String message) {
		super();
		this.message = message;
	}

	
}
