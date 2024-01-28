package com.school.sba.exception;

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
