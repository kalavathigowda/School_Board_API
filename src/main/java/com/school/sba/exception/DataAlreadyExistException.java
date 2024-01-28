package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataAlreadyExistException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public DataAlreadyExistException(String message) {
		super();
		this.message = message;
	}
	
}
