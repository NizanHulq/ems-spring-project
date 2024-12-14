package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeRequest;

public interface EmployeeService {
	
	EmployeeDTO saveEmployee(EmployeeRequest employeeRequest);
	List<EmployeeDTO> getAllEmployee(String sortBy);
	EmployeeDTO getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);
}
