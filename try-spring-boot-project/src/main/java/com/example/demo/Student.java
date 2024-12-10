package com.example.demo;

import java.util.Objects;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private double score;
	
	public Student(int id, String firstName, String lastName, double score) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", score=" + score + "]";
	}
	
	// Untuk membandingkan Student berdasarkan ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
