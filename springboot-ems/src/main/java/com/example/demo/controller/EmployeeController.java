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

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeRequest;
import com.example.demo.repository.DepartmentRepository;
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
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {

		    // Save the employee with department, project, and address
		    EmployeeDTO savedEmployee = employeeService.saveEmployee(
		        employeeRequest
		    );

		    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

	}

	// build get all employee Rest API
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) String sortBy) {
        List<EmployeeDTO> employees = employeeService.getAllEmployee(sortBy != null ? sortBy : "");
        return ResponseEntity.ok(employees);
    }
	
	// build get employee by id Rest API
	
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") long id){
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(id),HttpStatus.OK);
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
