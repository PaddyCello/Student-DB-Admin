package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
	
	private List<Student> allStudents = new ArrayList<Student>();
	
	//Method for creating new Student from user input
	public int[] createStudent(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		System.out.println("How many students are you adding?");
		
		try {
			int numOfTimes = Integer.parseInt(scanner.nextLine());
		
		if (numOfTimes < 1) {
			scanner.close();
			return null;
		}
		ArrayList<Integer> studentYears = new ArrayList<Integer>();
		
		for (int i = 0; i < numOfTimes; i++) {
			System.out.println("What is your first name?");
			String firstName = scanner.nextLine();
			System.out.println("What is your last name?");
			String lastName = scanner.nextLine();
			System.out.println("What is your year?");
		
			String year = scanner.nextLine();
			
			try {
				int numYear = Integer.parseInt(year);
			if (numYear > 0 && numYear < 8) {
				studentYears.add(numYear);
				Student student = new Student(firstName, lastName, numYear);
				allStudents.add(student);
			}
			} catch (NumberFormatException e) {
				System.out.println(e);
				continue;
			}
		}
		scanner.close();
		int[] studentYearPrimitive = new int[studentYears.size()];
		
		for (int i = 0; i < studentYears.size(); i++) {
			studentYearPrimitive[i] = studentYears.get(i);
		}
		return studentYearPrimitive;
		
		} catch (NumberFormatException e) {
			System.out.println(e);
			scanner.close();
			return null;
		}
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

	public static void main(String[] args) {

	}

}
