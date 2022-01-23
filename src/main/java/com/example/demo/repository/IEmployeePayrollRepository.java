package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.EmployeePayrollData;


public interface IEmployeePayrollRepository extends JpaRepository<EmployeePayrollData, Long> {
	public Optional<EmployeePayrollData> findByEmailId(String email_id);
//	@Query(value = "select * from employee_payroll,employee_department where emp_id=id and department= :department", nativeQuery = true)
//	List<EmployeePayrollData> findEmployeesByDepartment(String department);

//	public User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);
}
