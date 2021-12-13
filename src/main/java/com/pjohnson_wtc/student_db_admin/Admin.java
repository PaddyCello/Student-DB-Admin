package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
	
	private List<Student> allStudents = new ArrayList<Student>();
	
	//Method for creating new Student from user input
	public int[] createStudent(InputStream inputStream) throws NumberFormatException {
		
		//Create new Scanner and take user input
		Scanner scanner = new Scanner(inputStream);
		
		System.out.println("How many students are you adding?");
		
		//Throw NumberFormatException if user doesn't enter valid number
		try {
			
			int numOfTimes = Integer.parseInt(scanner.nextLine());
		
		//Return early if user enters number lower than one
		if (numOfTimes < 1) {
			scanner.close();
			return null;
		}
		
		//Create new ArrayList to hold IDs of successfully created Students
		ArrayList<Integer> studentYears = new ArrayList<Integer>();
		
		//Loop through as many times as user wishes to create Students
		for (int i = 0; i < numOfTimes; i++) {
			
			//Prompt user for details of Student and save to local variables
			System.out.println("What is your first name?");
			String firstName = scanner.nextLine();
			System.out.println("What is your last name?");
			String lastName = scanner.nextLine();
			System.out.println("What is your year?");
			String year = scanner.nextLine();
			
			//Validate year and create student, throw NumberFormatException if year not valid
			try {
				
				allStudents.add(checkYearAndCreateStudent(year, studentYears, firstName, lastName));
				
			} catch (NumberFormatException e) {
				System.out.println(e);
				continue;
			}
		}
		
		//Close scanner and return array of student IDs
		scanner.close();
		return formatStudentYears(studentYears);
		
		} catch (NumberFormatException e) {
			System.out.println(e);
			scanner.close();
			return null;
		}
	}
	
	//Convert ArrayList<Integer> to int[]
	private int[] formatStudentYears(ArrayList<Integer> studentYears) {
		int[] studentYearPrimitive = new int[studentYears.size()];
		
		for (int i = 0; i < studentYears.size(); i++) {
			studentYearPrimitive[i] = studentYears.get(i);
		}
		return studentYearPrimitive;
		
	}
	
	//Check for valid year and create Student from input
	private Student checkYearAndCreateStudent(String year, ArrayList<Integer> studentYears, String firstName, String lastName) {
		
		int numYear = Integer.parseInt(year);
		
		//Create Student if year valid, else return null
		if (numYear > 0 && numYear < 8) {
			
			studentYears.add(numYear);
			Student student = new Student(firstName, lastName, numYear);
			return student;
		
		} else {
			
			return null;
		}
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

	public static void main(String[] args) {

	}

}
