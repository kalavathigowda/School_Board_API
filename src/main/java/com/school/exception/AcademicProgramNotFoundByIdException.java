package com.school.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AcademicProgramNotFoundByIdException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public AcademicProgramNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
}
