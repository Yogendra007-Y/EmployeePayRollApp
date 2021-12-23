package com.example.demo.data;

public class EmployeePayrollData {
	public long id;
	public String name;
	public long salary;
	
	public EmployeePayrollData(long id, String name, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeePayrollDTO [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}