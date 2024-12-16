package com.example.demo.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.Project;

import lombok.Data;

@Data
public class EmployeeDTO {
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean status;
    private LocalDateTime createdAt;
    private String departmentName;
    private List<ProjectDTO> projects;
    
    public EmployeeDTO(Long id, String firstName, String lastName, String email, boolean status, LocalDateTime createdAt, String departmentName, List<ProjectDTO> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.departmentName = departmentName;
        this.projects = projects;
    }
}
