package com.school.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class AdminAlreadyExitsException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}
	public AdminAlreadyExitsException(String message) {
		super();
		this.message = message;
	}
}
