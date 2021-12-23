package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.IEmployeePayrollRepository;



@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeePayrollRepository employeerepository;
	

	/**
	 * Call get method to get details from table
	 * @param empId
	 * @return
	 */
	@Override
	public Employee getDetails(Long empId) {
		return employeerepository.findById(empId).get();
	}
	
	/**
	 * Call post method to add details in the table
	 * @param employee
	 * @return
	 */
	@Override
	public Employee addDetails(Employee employee) {
		return employeerepository.save(employee);
	}
	
	/**
	 * Call put method to edit details in the table
	 * @param employee
	 * @return
	 */
	@Override
	public Employee editDetails(Employee employee) {
		return employeerepository.save(employee);
	}
	
	/**
	 * Call delete method to remove details from table
	 * @param empId
	 * @return
	 */
	@Override
	public Employee deleteEmpId(Long empId) {
		Optional<Employee> isPresent = employeerepository.findById(empId);
		if(isPresent.isPresent()) {
			employeerepository.delete(isPresent.get());
			return isPresent.get();
		}else
			return null;
	}
	
}