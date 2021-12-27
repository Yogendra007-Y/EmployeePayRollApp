package com.example.demo.exceptions;

/**
 * Creating custom Exception
 */
public class EmployeePayrollException extends RuntimeException {

	public EmployeePayrollException(String message) {
		super(message);
	}

}