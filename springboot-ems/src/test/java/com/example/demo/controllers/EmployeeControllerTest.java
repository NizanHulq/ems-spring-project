package com.example.demo.controllers;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.dto.EmployeeRequest;
import com.example.demo.model.dto.ProjectDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	void getAllEmployees() throws Exception {
		// Mocking the service response
		List<ProjectDTO> projects = List.of(new ProjectDTO(1L, "Project Alpha", "Description", LocalDate.now(), LocalDate.now().plusDays(30)));
        List<EmployeeDTO> employees = List.of(
                new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", true, LocalDateTime.now(), "IT", projects),
                new EmployeeDTO(2L, "Jane", "Smith", "jane.smith@example.com", false, LocalDateTime.now(), "HR", projects)
        );
        
        when(employeeService.getAllEmployee(anyString())).thenReturn(employees);
		
        mockMvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].departmentName").value("HR"));
        verify(employeeService, times(1)).getAllEmployee(anyString());           
	}
	
	
	@Test
    void testGetEmployeeById() throws Exception {
        List<ProjectDTO> projects = List.of(new ProjectDTO(1L, "Project Alpha", "Description", LocalDate.now(), LocalDate.now().plusDays(30)));
        EmployeeDTO employee = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", true, LocalDateTime.now(), "IT", projects);

        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }
	
	@Test
    void testSaveEmployee() throws Exception {
        EmployeeRequest.AddressRequest addressRequest = new EmployeeRequest.AddressRequest();
        addressRequest.setStreet("123 Main St");
        addressRequest.setCity("Metropolis");
        addressRequest.setState("CA");
        addressRequest.setPostalCode("12345");

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setFirstName("John");
        employeeRequest.setLastName("Doe");
        employeeRequest.setEmail("john.doe@example.com");
        employeeRequest.setStatus(true);
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setProjectIds(List.of(1L));
        employeeRequest.setAddress(addressRequest);

        List<ProjectDTO> projects = List.of(new ProjectDTO(1L, "Project Alpha", "Description", LocalDate.now(), LocalDate.now().plusDays(30)));
        EmployeeDTO savedEmployee = new EmployeeDTO(1L, "John", "Doe", "john.doe@example.com", true, LocalDateTime.now(), "IT", projects);

        when(employeeService.saveEmployee(any(EmployeeRequest.class))).thenReturn(savedEmployee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.departmentName").value("IT"));

        verify(employeeService, times(1)).saveEmployee(any(EmployeeRequest.class));
    }
	
	@Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee has deleted!!"));

        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}
