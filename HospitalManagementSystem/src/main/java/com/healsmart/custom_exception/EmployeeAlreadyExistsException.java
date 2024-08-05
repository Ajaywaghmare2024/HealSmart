package com.healsmart.custom_exception;

@SuppressWarnings("serial")
public class EmployeeAlreadyExistsException extends RuntimeException {
	public EmployeeAlreadyExistsException(String msg) {
		super(msg);
	}
}
