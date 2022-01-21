package com.example.demo.controller;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeePayrollDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.EmployeePayrollData;
import com.example.demo.service.EmployeePayrollService;
import com.example.demo.service.IEmployeePayrollService;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.util.TokenUtil;


@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeePayrollController {

	@Autowired
	private IEmployeePayrollService employeePayrollService;
	   @Autowired
	    TokenUtil tokenutil;
		
		
		@PostMapping("/create")
		public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO){
			ResponseDTO respDTO = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
			
			
		}
		
	
	/**
	 * Call methdod to get employee details
	 * @return : Employee details
	 */
	@RequestMapping(value = {"/getAll" })
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@RequestHeader String token) {
			List<EmployeePayrollData> empDataList = null;
			empDataList = employeePayrollService.getEmployeePayrollData(token);
			ResponseDTO respDTO = new ResponseDTO("Get Call Success", empDataList, HttpStatus.ACCEPTED);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		}


	/**
	 * Call get method 
	 * @param empId : Employee id
	 * @return : Employee details with id
	 */
	@GetMapping("/get/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") Long empId , @RequestHeader String token) {
		EmployeePayrollData employeePayrollData = employeePayrollService.getEmployeePayrollDataById(empId, token);
		ResponseDTO respDTO = new ResponseDTO("Get call for ID Successful:", employeePayrollData, HttpStatus.ACCEPTED);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	

	/**
	 * Call put method
	 * @param empId : Employee id
	 * @param empPayrollDTO : name, salary
	 * @return : Employee details
	 */
	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@Valid @PathVariable("empId") Long empId,
			@RequestBody EmployeePayrollDTO empPayrollDTO , @RequestHeader String token) {
		ResponseDTO respDTO = employeePayrollService.updateEmployeePayrollData(empPayrollDTO,token,empId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);

	}

	/**
	 * Call delete method
	 * @param empId : Employee id
	 * @return : Deletion status with id
	 */
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") Long empId , @RequestHeader String token) {
		ResponseDTO respDTO = employeePayrollService.deleteEmployeePayrollData(empId , token);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	/**
	 * getting emp info by dept.
	 * @param department
	 * @return : emp details by departments and httpStatus
	 */
//	@GetMapping("/department/{department}")
//	public ResponseEntity<ResponseDTO> getEmployeeByDepartment(@PathVariable String department , String token) {
//
//		List<EmployeePayrollData> employeeList = null;
//		employeeList = employeePayrollService.getEmployeesPayrollDataByDepartment(department);
//		ResponseDTO response = new ResponseDTO("Get Call for Department Successful", employeeList, HttpStatus.ACCEPTED);
//		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
//	}
	
}