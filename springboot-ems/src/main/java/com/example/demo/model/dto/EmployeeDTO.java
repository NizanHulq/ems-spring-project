package com.example.demo.model.dto;

import java.time.LocalDateTime;

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
    
    public EmployeeDTO(Long id, String firstName, String lastName, String email, boolean status, LocalDateTime createdAt, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.departmentName = departmentName;
    }
}
