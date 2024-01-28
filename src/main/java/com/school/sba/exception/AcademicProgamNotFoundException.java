package com.school.sba.exception;

public class AcademicProgamNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getMessage() {
		return message;
	}


	public AcademicProgamNotFoundException(String message)
	{
		this.message=message;
	}
}
