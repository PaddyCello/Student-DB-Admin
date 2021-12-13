package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
	
	private List<Student> allStudents = new ArrayList<Student>();
	
	//Method for creating new Student from user input
	public Integer createStudent(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		System.out.println("What is your first name?");
		String firstName = scanner.nextLine();
		System.out.println("What is your last name?");
		String lastName = scanner.nextLine();
		System.out.println("What is your year?");
		
		int year = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
		if (year < 1 || year > 7) return null;
		
		Student student = new Student(firstName, lastName, year);
		allStudents.add(student);
		
		return year;
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is your name?");
		
		String name = scanner.nextLine();
		scanner.close();
		
		System.out.println("Nice to meet you, " + name);

	}

}
