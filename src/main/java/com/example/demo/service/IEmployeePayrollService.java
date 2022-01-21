package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.User;

import com.example.demo.dto.EmployeePayrollDTO;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.EmployeePayrollData;



public interface IEmployeePayrollService {

	EmployeePayrollData getEmployeePayrollDataById(Long empId, String token);

	ResponseDTO createEmployeePayrollData(@Valid EmployeePayrollDTO empPayrollDTO);

	List<EmployeePayrollData> getEmployeePayrollData(String token);

	
	ResponseDTO deleteEmployeePayrollData(Long empId, String token);


//	List<EmployeePayrollData> getEmployeesPayrollDataByDepartment(String department, String token);

	ResponseDTO updateEmployeePayrollData(EmployeePayrollDTO empPayrollDTO, String token, @Valid Long empId);

	ResponseDTO LoginEmployeePayrollData(@Valid LoginDto loginDto);

	


}