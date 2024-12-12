//package com.example.demo.model;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.Set;
//
//@Data
//@Entity
//@Table(name = "projects")
//public class Project {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//        name = "employee_projects",
//        joinColumns = @JoinColumn(name = "project_id"),
//        inverseJoinColumns = @JoinColumn(name = "employee_id")
//    )
//    private Set<Employee> employees;
//}