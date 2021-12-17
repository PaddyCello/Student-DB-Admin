package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

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
				
				checkYearAndCreateStudent(stringYear, studentIDs, firstName, lastName);
				
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
		
		//Early return if year is not valid
		if (year < 1 || year > 7) {
			
			logger.log(Level.WARNING, "Invalid year.");
			return null;
		}
		
		//Initialize boolean variable, to declare if new Student has been found in allStudents
		boolean alreadyInList = false;
		
		//Loop through allStudents, checking to see if any existing entries have first name, last name and year from argument list
		for (Student oneStudent : allStudents) {
			
			if (oneStudent.getFirstName().equals(firstName) &&
				oneStudent.getLastName().equals(lastName) &&
				oneStudent.getYear() == year) {
				
					//If so, set boolean to true and break the loop
					alreadyInList = true;
					logger.log(Level.WARNING, "Student already in database.");
					break;
			}
		}
		
		//Create Student if Student not in list, else return null
		if (!alreadyInList) {
			
				Student student = new Student(firstName, lastName, year);
				
				studentIDs.add(student.getStudentId());
				
				allStudents.add(student);
				
				logger.log(Level.INFO, "New Student created.");
				return student;
				
			//Otherwise, return null	
			} else {
				
				return null;
				
			}
	}
	//WTCET-19 - NEW until 154
	//Show Student status - pass Student ID as an argument
	public String showStatus(int studentId) {
		
		//Initialize String to receive Student details when found
		String correctStudent = null;
		
		//Loop through list of Students, checking to see if the ID matches the one passed
		for (Student student : allStudents) {
			
			//If so, assign to correctStudent and break the loop
			if (student.getStudentId() == studentId) {
				
				correctStudent = student.toString();
				break;
			}
		}
		
		//Log a warning if an invalid ID has been passed, or a confirmation if Student found
		if (correctStudent == null) {
			logger.log(Level.WARNING, "ID does not exist.");
		} else {
			logger.log(Level.INFO, "Student found successfully.");
		}
		
		//Return either the Student info or null, depending on the outcome of the loop
		return correctStudent;
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

}
