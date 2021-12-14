package com.pjohnson_wtc.student_db_admin;

public class Student {
	
	//Instance variables
	private String firstName;
	private String lastName;
	private int year;
	private int studentId;
	
	//Constructor for Student
	public Student(String firstName, String lastName, int year) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.year = year;
		this.studentId = generateStudentId(year);
	}
	
	//Generate StudentID: year followed by four random digits
	private int generateStudentId(int year) {
		
		int studentId = (year * 10000) + (int)(Math.random() * 9999);
		return studentId;
	}
	
	//Necessary getters
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getYear() {
		return year;
	}
	public int getStudentId() {
		return studentId;
	}
}
