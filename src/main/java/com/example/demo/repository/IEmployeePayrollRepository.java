package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.EmployeePayrollData;

public interface IEmployeePayrollRepository extends JpaRepository<EmployeePayrollData, Integer> {

}