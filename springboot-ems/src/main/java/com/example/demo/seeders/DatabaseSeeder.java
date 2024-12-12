package com.example.demo.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeAddressRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressRepository employeeAddressRepository;

    public DatabaseSeeder(DepartmentRepository departmentRepository, ProjectRepository projectRepository,
                          EmployeeRepository employeeRepository, EmployeeAddressRepository employeeAddressRepository) {
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.employeeAddressRepository = employeeAddressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDepartments();
        seedProjects();
        seedEmployeesAndAddresses();
    }

    private void seedDepartments() {
        if (departmentRepository.count() == 0) {
            Department hr = new Department("Human Resources", 50000.00, LocalDate.of(2010, 1, 1));
            Department it = new Department("Information Technology", 200000.00, LocalDate.of(2005, 6, 15));
            Department marketing = new Department("Marketing", 100000.00, LocalDate.of(2015, 9, 20));
            departmentRepository.saveAll(Arrays.asList(hr, it, marketing));
        }
    }

    private void seedProjects() {
        if (projectRepository.count() == 0) {
            Project project1 = new Project("Project Alpha",
            		"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 30));
            Project project2 = new Project("Project Beta",
            		"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", LocalDate.of(2023, 7, 1), LocalDate.of(2024, 3, 31));
            Project project3 = new Project("Project Gamma",
            		"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", LocalDate.of(2023, 9, 1), LocalDate.of(2024, 5, 15));
            Project project4 = new Project("Project Delta",
            		"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", LocalDate.of(2022, 11, 1), LocalDate.of(2023, 8, 31));
            Project project5 = new Project("Project Epsilon",
            		"Lorem Ipsum is simply dummy text of the printing and typesetting industry.", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 12, 31));
            projectRepository.saveAll(Arrays.asList(project1, project2, project3, project4, project5));
        }
    }

    private void seedEmployeesAndAddresses() {
        if (employeeRepository.count() == 0) {
            Department it = departmentRepository.findByName("Information Technology").orElse(null);
            Department hr = departmentRepository.findByName("Human Resources").orElse(null);

            Employee emp1 = new Employee("John", "Doe", "john.doe@example.com", true, LocalDateTime.now(), it);
            Employee emp2 = new Employee("Jane", "Smith", "jane.smith@example.com", true, LocalDateTime.now(), hr);
            Employee emp3 = new Employee("Alice", "Johnson", "alice.johnson@example.com", true, LocalDateTime.now(), it);

            employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3));

            EmployeeAddress addr1 = new EmployeeAddress("123 Main St", "New York", "NY", "10001", emp1);
            EmployeeAddress addr2 = new EmployeeAddress("456 Oak Ave", "Los Angeles", "CA", "90001", emp2);
            EmployeeAddress addr3 = new EmployeeAddress("789 Pine Rd", "Chicago", "IL", "60601", emp3);

            employeeAddressRepository.saveAll(Arrays.asList(addr1, addr2, addr3));
        }
    }
}
