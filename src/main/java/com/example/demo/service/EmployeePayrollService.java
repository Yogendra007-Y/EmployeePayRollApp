package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeePayrollDTO;
import com.example.demo.exceptions.EmployeePayrollException;
import com.example.demo.model.EmployeePayrollData;
import com.example.demo.repository.IEmployeePayrollRepository;



@Service
public class EmployeePayrollService implements IEmployeePayrollService {
	@Autowired
	private IEmployeePayrollRepository employeeRepository;

	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

	/**
	 * get employee details
	 * 
	 * @return : Employee details
	 */
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeePayrollList;
	}

	/**
	 * get employee details
	 * 
	 * @return : Employee detail using id
	 */
	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeePayrollList.stream().filter(empData -> empData.getEmployeeId() == empId).findFirst()
				.orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
	}

	/**
	 * post employee details
	 * 
	 * @return : Employee details with id
	 */
	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		return employeeRepository.save(empData);
	}

	/**
	 * update employee details
	 * 
	 * @return : updated Employee details
	 */
	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.setName(empPayrollDTO.name);
		empData.setSalary(empPayrollDTO.salary);
		employeePayrollList.set(empId - 1, empData);
		return empData;
	}

	/**
	 * Call delete method
	 */
	@Override
	public void deleteEmployeePayrollData(int empId) {
		employeePayrollList.remove(empId - 1);
	}

}