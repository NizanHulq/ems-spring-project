package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProjectDTO {
	private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    
	public ProjectDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
    
    
}
