package com.healsmart.custom_exception;

@SuppressWarnings("serial")
public class NoSuchPatientFoundException extends RuntimeException {


	public NoSuchPatientFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
