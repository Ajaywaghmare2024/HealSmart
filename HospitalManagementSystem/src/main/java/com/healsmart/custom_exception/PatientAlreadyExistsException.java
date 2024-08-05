package com.healsmart.custom_exception;

@SuppressWarnings("serial")
public class PatientAlreadyExistsException extends RuntimeException {

	public PatientAlreadyExistsException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
