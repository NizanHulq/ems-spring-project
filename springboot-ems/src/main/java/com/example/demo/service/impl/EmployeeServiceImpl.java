package com.example.demo.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	// disini secara default ada autowired sehingga repository otomatis terinjeksi
	// ke service
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee(String sortBy) {
		// TODO Auto-generated method stub

		switch (sortBy) {
		case "firstName":
			// return sorted by newest data
			return employeeRepository.findAll().stream().sorted(Comparator.comparing(Employee::getFirstName)).toList();
		case "createdAt":
			// return sorted by newest data
			return employeeRepository.findAll().stream().sorted(Comparator.comparing(Employee::getCreatedAt).reversed())
					.toList();
		default:
			return employeeRepository.findAll(); // Return unsorted list if no match
		}

	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
//		Optional<Employee> employee = employeeRepository.findById(id);
//		
//		if(employee.isPresent()) {
//			return employee.get();
//		} else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//		}

		// this is using lambda expression
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub

		// we need to check employee given by id is existing or not
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setStatus(employee.isStatus());
		existingEmployee.setCreatedAt(employee.getCreatedAt());

		employeeRepository.save(existingEmployee);

		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		// check a employee is exist or not

		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		employeeRepository.deleteById(id);
	}

}
