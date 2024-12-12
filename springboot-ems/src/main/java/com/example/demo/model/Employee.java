package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	// Kolom untuk mencatat waktu pembuatan data
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    // Kolom untuk menyimpan status aktif atau tidak aktif
    @Column(name = "status", nullable = false)
    private boolean status;
    
//    @ManyToOne
//    @JoinColumn(name = "department_id", nullable = false) // ini masih bermasalah karena sudah ada data sebelumnya
//    private Department department;
//    
//    @ManyToMany(mappedBy = "employees")
//    private Set<Project> projects;
    
    public Employee() {
        this.createdAt = LocalDateTime.now(); // Default ke waktu sekarang
        this.status = true; // Default ke status aktif
    }
}
