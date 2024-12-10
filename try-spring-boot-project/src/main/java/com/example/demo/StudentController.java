package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	// this ex for return list of object on restApi
	@GetMapping("/students")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();

		students.add(new Student(1,"nizan", "Dhia", 80));
		students.add(new Student(2,"Hayabusa", "Dhia", 90));
		students.add(new Student(3,"paijo", "Dhia", 70));
		students.add(new Student(4,"Mamang", "Dhia", 60));
		students.add(new Student(5,"Kurama", "Hanabi", 100));

		return students;
	}
	
	@GetMapping("/students/pass")
	public List<Student> getStudentsPass() {
		List<Student> students = new ArrayList<>();

		students.add(new Student(1,"nizan", "Dhia", 80));
		students.add(new Student(2,"Hayabusa", "Dhia", 90));
		students.add(new Student(3,"paijo", "Dhia", 70));
		students.add(new Student(4,"Mamang", "Dhia", 60));
		students.add(new Student(5,"Kurama", "Hanabi", 100));

		List<Student> studentPass = students.stream().filter(student->student.getScore()>=70).toList();
		
		return studentPass;
	}
	
	
	// this just ex for make restApi Get
//	@GetMapping("/student")
//	public Student getStudent() {
//		return new Student("Nizan", "Dhialu");
//	}

	// http://localhost:8080/students/id
	// id on that path name is path variable
//	@GetMapping("/student/{firstName}/{lastName}")
//	public Student studentPathVariable(@PathVariable String firstName, @PathVariable String lastName) {
//		return new Student(firstName, lastName);
//	}

	// http://localhost:8080/students?firstName=nizan&lastName=dhia
	// This is Query parameter usefull for passing data key value to other object which need it 
//	@GetMapping("/student/query")
//	public Student studentQueryParams(
//			@RequestParam(name="firstName") String firstName, 
//			@RequestParam(name="lastName") String lastName) {
//		return new Student(firstName, lastName);
//	}
}
