package com.example.demo.service;

import com.example.demo.model.Employee;

public interface IEmployeeService {
	public Employee getDetails(Long empId); 
	public Employee addDetails(Employee employee);
	public Employee editDetails(Employee employee);
	public Employee deleteEmpId(Long empId);
}
