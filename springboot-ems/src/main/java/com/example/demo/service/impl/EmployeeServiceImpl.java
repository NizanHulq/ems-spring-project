package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.Project;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeRequest;
import com.example.demo.model.dto.ProjectDTO;
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
			EmployeeAddressRepository employeeAddressRepository, ProjectRepository projectRepository,
			DepartmentRepository departmentRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeAddressRepository = employeeAddressRepository;
		this.projectRepository = projectRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public EmployeeDTO saveEmployee(EmployeeRequest employeeRequest) {

		// Fetch the related project
		List<Project> projects = projectRepository.findAllById(employeeRequest.getProjectIds());
		List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> new ProjectDTO(
                		project.getId(),
                		project.getName(),
                		project.getDescription(),
                		project.getStartDate(),
                		project.getEndDate()
                		))
                .collect(Collectors.toList());

		// Fetch the related department
		Department department = departmentRepository.findById(employeeRequest.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department", "Id", employeeRequest.getDepartmentId()));

		Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
				employeeRequest.getEmail(), employeeRequest.isStatus(), LocalDateTime.now(), department);

		// Create EmployeeAddress from the request
		EmployeeAddress address = new EmployeeAddress(employeeRequest.getAddress().getStreet(),
				employeeRequest.getAddress().getCity(), employeeRequest.getAddress().getState(),
				employeeRequest.getAddress().getPostalCode(), employee);

		// Set relationships
		employee.setProjects(projects);
		// Add the employee to each project's employee list
	    projects.forEach(project -> project.getEmployees().add(employee));
	    

		employee.setDepartment(department); // Assuming Many-to-One
		address.setEmployee(employee);
		employee.setAddress(address);

		// Save employee and address
		Employee savedEmployee = employeeRepository.save(employee);
		employeeAddressRepository.save(address);

		return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getFirstName(), savedEmployee.getLastName(),
				savedEmployee.getEmail(), savedEmployee.isStatus(), savedEmployee.getCreatedAt(), department.getName(),projectDTOs);
	}

	@Override
	public List<EmployeeDTO> getAllEmployee(String sortBy) {
		// TODO Auto-generated method stub
		List<Employee> employees;

		switch (sortBy) {
		case "firstName":
			// return sorted by newest data
			employees = employeeRepository.findAll().stream().sorted(Comparator.comparing(Employee::getFirstName))
					.toList();
		case "createdAt":
			// return sorted by newest data
			employees = employeeRepository.findAll().stream()
					.sorted(Comparator.comparing(Employee::getCreatedAt).reversed()).toList();
		default:
			employees = employeeRepository.findAll(); // Return unsorted list if no match
		}

		return employees.stream().map(EmployeeMapper::toEmployeeDTO).toList();

	}

	@Override
	public EmployeeDTO getEmployeeById(long id) {
		// TODO Auto-generated method stub

		Employee savedEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		String departmentName = Optional.ofNullable(savedEmployee.getDepartment()).map(Department::getName)
				.orElse("No Department");
		List<Project> projects = savedEmployee.getProjects();
		List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> new ProjectDTO(
                		project.getId(),
                		project.getName(),
                		project.getDescription(),
                		project.getStartDate(),
                		project.getEndDate()
                		))
                .collect(Collectors.toList());

		return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getFirstName(), savedEmployee.getLastName(),
				savedEmployee.getEmail(), savedEmployee.isStatus(), savedEmployee.getCreatedAt(), departmentName, projectDTOs);

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
