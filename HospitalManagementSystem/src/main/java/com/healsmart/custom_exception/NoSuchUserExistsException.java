package com.healsmart.custom_exception;

@SuppressWarnings("serial")
public class NoSuchUserExistsException extends RuntimeException{

	public NoSuchUserExistsException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
