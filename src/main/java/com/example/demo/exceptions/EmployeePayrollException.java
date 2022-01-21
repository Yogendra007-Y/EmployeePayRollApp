package com.example.demo.exceptions;

/**
 * Creating custom Exception
 */
public class EmployeePayrollException extends RuntimeException {
	/**
	 * 
	 */
	private int StatusCode;
	private String Statusmessage;
	public EmployeePayrollException(String message) {
		super(message);
	}
	
	public EmployeePayrollException( String statusmessage, int statusCode) {
		super(statusmessage);
		StatusCode =statusCode;
		Statusmessage = statusmessage;
	}
}