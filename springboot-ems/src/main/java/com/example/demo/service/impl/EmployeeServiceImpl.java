package com.example.demo.service.impl;

import java.util.Comparator;
import java.util.List;
//import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.Project;
import com.example.demo.model.dto.EmployeeRequest;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeAddressRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private EmployeeAddressRepository employeeAddressRepository;
	private ProjectRepository projectRepository;
	private DepartmentRepository departmentRepository;

	// disini secara default ada autowired sehingga repository otomatis terinjeksi
	// ke service
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, 
			EmployeeAddressRepository employeeAddressRepository, 
			ProjectRepository projectRepository,
			DepartmentRepository departmentRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeAddressRepository = employeeAddressRepository;
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
	}

	@Override
	public Employee saveEmployee(EmployeeRequest employeeRequest) {
			
		
		// Fetch the related project
	    Project project = projectRepository.findById(employeeRequest.getProjectId())
	        .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", employeeRequest.getProjectId()));

	    // Fetch the related department
	    Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
	        .orElseThrow(() -> new ResourceNotFoundException("Department", "Id", employeeRequest.getDepartmentId()));

	    Employee employee = new Employee(employeeRequest.getFirstName(), 
				employeeRequest.getLastName(),
				employeeRequest.getEmail(),
				employeeRequest.isStatus(),
				employeeRequest.getCreatedAt(),
				department
				);

		    // Create EmployeeAddress from the request
		    EmployeeAddress address = new EmployeeAddress(
		        employeeRequest.getAddress().getStreet(),
		        employeeRequest.getAddress().getCity(),
		        employeeRequest.getAddress().getState(),
		        employeeRequest.getAddress().getPostalCode(),
		        employee
		    );
	    
	    // Set relationships
	    employee.getProjects().add(project); // Assuming Many-to-Many
	    employee.setDepartment(department); // Assuming Many-to-One
	    address.setEmployee(employee);
	    employee.setAddress(address);

	    // Save employee and address
	    Employee result = employeeRepository.save(employee);
	    employeeAddressRepository.save(address);
	    return result;
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
