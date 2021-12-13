package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
	
	Logger logger = Logger.getLogger("com.pjohnson_wtc.student_db_admin.admin");
	
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
			
			logger.log(Level.WARNING, "Number of Students too low.");
			return null;
		}
		
		//Create new ArrayList to hold IDs of successfully created Students
		ArrayList<Integer> studentIDs = new ArrayList<Integer>();
		
		//Loop through as many times as user wishes to create Students
		for (int i = 0; i < numOfTimes; i++) {
			
			//Prompt user for details of Student and save to local variables
			System.out.println("What is your first name?");
			String firstName = scanner.nextLine();
			System.out.println("What is your last name?");
			String lastName = scanner.nextLine();
			System.out.println("What is your year?");
			String stringYear = scanner.nextLine();
			
			//Validate year and create student, throw NumberFormatException if year not valid
			try {
				
				allStudents.add(checkYearAndCreateStudent(stringYear, studentIDs, firstName, lastName));
				
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid number for Student year", e);
				continue;
			}
		}
		
		//Close scanner and return array of student IDs
		scanner.close();
		return formatStudentYears(studentIDs);
		
		} catch (NumberFormatException e) {
			logger.log(Level.WARNING, "Invalid number for number of Students", e);
			scanner.close();
			return null;
		}
	}
	
	//Convert ArrayList<Integer> to int[]
	private int[] formatStudentYears(ArrayList<Integer> studentIDs) {
		int[] studentIDPrimitive = new int[studentIDs.size()];
		
		for (int i = 0; i < studentIDs.size(); i++) {
			studentIDPrimitive[i] = studentIDs.get(i);
		}
		return studentIDPrimitive;
		
	}
	
	//Check for valid year and create Student from input
	private Student checkYearAndCreateStudent(String stringYear, ArrayList<Integer> studentIDs, String firstName, String lastName) {
		
		int year = Integer.parseInt(stringYear);
		
		//Create Student if year valid, else return null
		if (year > 0 && year < 8) {
			
			Student student = new Student(firstName, lastName, year);
			studentIDs.add(student.getStudentId());
			
			logger.log(Level.INFO, "New Student created.");
			return student;
		
		} else {
			
			logger.log(Level.WARNING, "Invalid year.");
			return null;
		}
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

	public static void main(String[] args) {

	}

}
