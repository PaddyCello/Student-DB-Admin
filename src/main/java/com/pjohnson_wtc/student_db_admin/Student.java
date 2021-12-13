package com.pjohnson_wtc.student_db_admin;

public class Student {
	private String firstName;
	private String lastName;
	private int year;
	private int studentId;
	
	public Student(String firstName, String lastName, int year) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.year = year;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public int getStudentId() {
		return studentId;
	}
}
