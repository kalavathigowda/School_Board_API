package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNotFoundByIdException  extends RuntimeException{
       
	      private String message;

		public UserNotFoundByIdException(String message) {
			super();
			this.message = message;
		}
    public void setMessage(String message) {
			this.message = message;
		}
}
