package com.example.demo.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.Project;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private boolean status;
    private LocalDateTime createdAt;
    private Long departmentId;
    private List<Long> projectIds;
    private AddressRequest address;

    @Data
    public static class AddressRequest {
        private String street;
        private String city;
        private String state;
        private String postalCode;

        // Getters and Setters
    }
}

