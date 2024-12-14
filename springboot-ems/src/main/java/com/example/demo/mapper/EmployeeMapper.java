package com.example.demo.mapper;

import com.example.demo.model.Employee;
import com.example.demo.model.dto.EmployeeDTO;

public class EmployeeMapper {

    // Convert Employee entity to EmployeeDTO
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        String departmentName = employee.getDepartment() != null ? employee.getDepartment().getName() : "No Department";
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.isStatus(),
                employee.getCreatedAt(),
                departmentName
        );
    }
}