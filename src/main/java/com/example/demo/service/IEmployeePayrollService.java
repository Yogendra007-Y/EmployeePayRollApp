package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeePayrollDTO;
import com.example.demo.model.EmployeePayrollData;



public interface IEmployeePayrollService {

	/**
	 * Method: get employee details
	 */
	public List<EmployeePayrollData> getEmployeePayrollData();

	/**
	 * Method: get employee detail by id
	 */
	public EmployeePayrollData getEmployeePayrollDataById(int empId);

	/**
	 * Method: add employee details
	 */
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO);

	/**
	 * Method: update employee details
	 */
	public EmployeePayrollData updateEmployeePayrollData( int empId,EmployeePayrollDTO empPayrollDTO);

	/**
	 * Method: delete employee details
	 */
	public void deleteEmployeePayrollData(int empId);
}