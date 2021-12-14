package com.pjohnson_wtc.student_db_admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Student {
	
	//Instance variables
	private String firstName;
	private String lastName;
	private int year;
	private int studentId;
	private List<String> enrolledCourses = new ArrayList<String>();
	private BigDecimal balance;
	
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
	
	//toString override
	public String toString() {
		return firstName + " " + lastName + ", " + studentId + ", Courses enrolled: " + String.join(", ", enrolledCourses) + ", balance $" + balance;
	}
	
	//Necessary setters
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	public List<String> getEnrolledCourses() {
		return enrolledCourses;
	}
}
