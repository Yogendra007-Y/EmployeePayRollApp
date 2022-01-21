package com.example.demo.service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EmployeePayrollDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.exceptions.EmployeePayrollException;
import com.example.demo.model.EmployeePayrollData;
import com.example.demo.repository.IEmployeePayrollRepository;
import com.example.demo.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

	@Autowired
	private IEmployeePayrollRepository employeeRepository;

	@Autowired
    TokenUtil tokenUtil;
	
	@Autowired
	ModelMapper modelmapper;

	/**
	 * @param : usernamr password
	 * @return : token
	 * 
	 */
	@Override
	public ResponseDTO createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
//		if (tokenUtil.isValidToken(token)) {
//			EmployeePayrollData empData = null;
//			empData = new EmployeePayrollData(empPayrollDTO);
//			log.debug("Employee Data: " empData.toString())
//			return employeeRepository.save(empData);
//		}else
//			throw new EmployeePayrollException("Not Valid Token");
	Optional<EmployeePayrollData> isPresent = employeeRepository.findByEmailId(empPayrollDTO.getEmailId());
	if (isPresent.isPresent()) {
		throw new EmployeePayrollException("Employee Details Aleady Added", 400);
		
	}else {
		EmployeePayrollData contact = modelmapper.map(empPayrollDTO, EmployeePayrollData.class);
		employeeRepository.save(contact);
		String token1 = tokenUtil.createToken(contact.getEmployeeId());
		return new ResponseDTO("contact successfully added,token1,HttpsStatus.OK", token1, HttpStatus.OK);
		
	}
	}
	
	

	@Override
	public List<EmployeePayrollData> getEmployeePayrollData (String token) {
//		if (tokenUtil.isValidToken(token)) {
//			return employeeRepository.findAll();
//		} else
//			throw new EmployeePayrollException("Not Valid Token");
		Long id = tokenUtil.decodeToken(token);
		Optional<EmployeePayrollData> isEmployeePresent = employeeRepository.findById(id);
		if (isEmployeePresent.isPresent()) {
			List<EmployeePayrollData> getallemployees = employeeRepository.findAll();
			return getallemployees;
		}else {
			throw new EmployeePayrollException("Employee Details Aleady Added", 400);
		}
		
	}
	
	/**
	 * Using Get method to get the employee by id
	 * 
	 * @param empId : Employee Id
	 * @return : It will return details of employee
	 */

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(Long empId, String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<EmployeePayrollData> isEmployeePresent = employeeRepository.findById(id);
		if (isEmployeePresent.isPresent()) {
			return employeeRepository.findById(empId)
					.orElseThrow(() -> new EmployeePayrollException("employee ID Not Found"));
		} else
			throw new EmployeePayrollException("Not Valid Token");
	}
	

	
	/**
	 * Using put method to update the details
	 * 
	 * @param empPayrollDTO : Employee name and salary
	 * @return : employee id, name and salary
	 */

	@Override
	public ResponseDTO updateEmployeePayrollData(EmployeePayrollDTO empPayrollDTO, String token, @Valid Long empId) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId, token);
		Long id = tokenUtil.decodeToken(token);
		Optional<EmployeePayrollData> isEmployeePresent = employeeRepository.findById(id);
		if (isEmployeePresent.isPresent()) {
			empData.updateEmployeePayroll(empPayrollDTO);
			employeeRepository.save(isEmployeePresent.get());
			return new ResponseDTO("Contact Successfully Updated",empData,HttpStatus.ACCEPTED);
			
		}else {
			throw new EmployeePayrollException("400,Not Valid Token");
			
		}

	}
	
	/**
	 * Using delete method to delete the employee details
	 * 
	 * @param empId : Employee id
	 * @return : will return string for deleted id
	 */

	@Override
	public ResponseDTO deleteEmployeePayrollData( Long empId ,String token) {
//		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId, token);
		Long id = tokenUtil.decodeToken(token);
		Optional<EmployeePayrollData> isEmployeePresent = employeeRepository.findById(id);
		if (isEmployeePresent.isPresent()) {
			employeeRepository.delete(isEmployeePresent.get());
		}
			return new ResponseDTO("Deleted Successfully ",id,HttpStatus.ACCEPTED);
				
		}


	

//	@Override
//	public List<EmployeePayrollData> getEmployeesPayrollDataByDepartment(String department,String token) {
//		return null;
//	
//	
//	
//	
//	}

}
