package com.school.sba.util;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.school.exception.UserNotFoundByIdException;

public class ApplicationExceptionHandler {

	private ResponseEntity<Object> structure(HttpStatus status,String message,Object rootCause){
	      return new  ResponseEntity<Object> (Map.of(
	    		  "status",status.value(),
	    		  "message",message,
	    		  "rootCause",rootCause
	    		  ),status);
	}
	
  @ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex)
	{
	return structure(HttpStatus.NOT_FOUND,ex.getMessage(),"User not found with the given id");
	}

   public ResponseEntity<Object> handleRuntime(RuntimeException ex)
  {
      return structure(HttpStatus.BAD_REQUEST,ex.getMessage(),"");
  }

}
