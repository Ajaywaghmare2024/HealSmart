package com.healsmart.custom_exception;

@SuppressWarnings("serial")
public class NoSuchEmployeeExistsException extends RuntimeException {
	public NoSuchEmployeeExistsException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
