package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// build create employee Rest API
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build get all employee Rest API
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String sortBy) {
        List<Employee> employees = employeeService.getAllEmployee(sortBy != null ? sortBy : "");
        return ResponseEntity.ok(employees);
    }
//	public List<Employee> getAllEmployee() {
//		return employeeService.getAllEmployee();
//	}
	
	// build get employee by id Rest API
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") long id){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
	// build update employee Rest API
	
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name="id") long id, 
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	// build delete employee Rest API
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") long id){
		
		// delete employe by id on database
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee has deleted!!",HttpStatus.OK);
	}
	
}
