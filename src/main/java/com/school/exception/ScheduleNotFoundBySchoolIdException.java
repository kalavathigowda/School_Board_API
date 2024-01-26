package com.school.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleNotFoundBySchoolIdException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public ScheduleNotFoundBySchoolIdException(String message) {
		super();
		this.message = message;
	}
	
	
}
