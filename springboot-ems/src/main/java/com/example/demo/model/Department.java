package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor // Tambahkan default constructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double budget;

    @Column(nullable = false)
    private LocalDate foundedDate;

    // Tambahkan constructor tanpa id untuk memudahkan seeding
    public Department(String name, Double budget, LocalDate foundedDate) {
        this.name = name;
        this.budget = budget;
        this.foundedDate = foundedDate;
    }
}

