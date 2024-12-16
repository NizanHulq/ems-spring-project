package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.ProjectDTO;

public class EmployeeMapper {

    // Convert Employee entity to EmployeeDTO
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        String departmentName = employee.getDepartment() != null ? employee.getDepartment().getName() : "No Department";
        List<ProjectDTO> projectDTOs = employee.getProjects().stream()
                .map(project -> new ProjectDTO(
                		project.getId(),
                		project.getName(),
                		project.getDescription(),
                		project.getStartDate(),
                		project.getEndDate()
                		))
                .collect(Collectors.toList());
        
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.isStatus(),
                employee.getCreatedAt(),
                departmentName,
                projectDTOs
        );
    }
}