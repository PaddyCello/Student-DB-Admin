package com.pjohnson_wtc.student_db_admin;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class Admin {
	
	Logger logger = Logger.getLogger("com.pjohnson_wtc.student_db_admin.admin");
	
	private List<Student> allStudents = new ArrayList<Student>();
	private String[] courses = {"History 101", "Mathematics 101", "English 101", "Chemistry 101", "Computer Science 101"};
	
	//Method for creating new Student from user input
	public int[] createStudent(InputStream inputStream) {
		
		//Create new ArrayList to hold IDs of successfully created Students
		ArrayList<Integer> studentIDs = null;
		
		//Create new Scanner and take user input
		Scanner scanner = new Scanner(inputStream);
		
		logger.log(Level.INFO, "How many students are you adding?");
		
		//Throw NumberFormatException if user doesn't enter valid number
		try {
			
			int numOfTimes = Integer.parseInt(scanner.nextLine());
		
		//Return early if user enters number lower than one
		if (numOfTimes < 1) {
			scanner.close();
			
			logger.log(Level.WARNING, "Number of Students too low.");
			return null;
		}
		
		//Loop through as many times as user wishes to create Students
		for (int i = 0; i < numOfTimes; i++) {
			
			//Prompt user for details of Student and save to local variables
			logger.log(Level.INFO, "What is your first name?");
			String firstName = scanner.nextLine();
			logger.log(Level.INFO, "What is your last name?");
			String lastName = scanner.nextLine();
			logger.log(Level.INFO, "What is your year?");
			String stringYear = scanner.nextLine();
			
			
			//Validate year and create student, throw NumberFormatException if year not valid
			try {
				
				//Initialize studentIDs
				studentIDs = new ArrayList<Integer>();
				
				validateStudent(stringYear, studentIDs, firstName, lastName);
				
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
	private Student validateStudent(String stringYear, ArrayList<Integer> studentIDs, String firstName, String lastName) {
		
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
		return (!alreadyInList) ? finallyMakeStudent(year, studentIDs, firstName, lastName) : null;
	}
	
	//Method for making student and adding to ArrayLists
	private Student finallyMakeStudent(int year, ArrayList<Integer> studentIDs, String firstName, String lastName) {
		
		logger.log(Level.INFO, "Creating Student.");
		
		Student student = new Student(firstName, lastName, year);
		
		studentIDs.add(student.getStudentId());
		
		allStudents.add(student);
		
		logger.log(Level.INFO, "New Student created.");
		
		return student;
	}
	//WTCET-20 - NEW
	//Show Student status - pass Student ID as an argument
	public String showStatus(int studentId) {
		
		//Initialize new Student to receive Student details when found
		Student correctStudent = getStudentById(studentId);
		
		//Return either the Student info or null, depending on the outcome of the method call
		return (correctStudent == null) ? null : correctStudent.toString();
	}
	
	//Enroll a student in a new course
	public String enrollInCourse(int studentId, String course) {
		
		//Find student by ID
		Student student = getStudentById(studentId);
		
		//Return early if course does not exist, ID is invalid, funds are insufficient or student already enrolled
		if (!checkCourses(course) || 
				student == null ||
				student.getBalance().compareTo(new BigDecimal(600)) < 0 ||
				!checkNotEnrolled(student, course)) return null;
		
		//Otherwise, add course to Student's list of enrolled courses
		student.getEnrolledCourses().add(course);
		
		//Subtract cost of course from student's balance
		student.setBalance(student.getBalance().subtract(new BigDecimal(600)));
		
		//Return Student status to display update
		return student.toString();
	}
	
	//Find Student by ID
	public Student getStudentById(int studentId) {
		
		//Set a new Student to null
		Student studentToReturn = null;
		
		//Loop through allStudents, looking for a match to the ID that was passed as an argument
		for (Student student : allStudents) {
			
			if (student.getStudentId() == studentId) {
				
				//If successful, assign to studentToReturn and break the loop
				studentToReturn = student;
				break;
			}
		}
		//Log a warning if an invalid ID has been passed
		if (studentToReturn == null) logger.log(Level.WARNING, "ID does not exist.");
		
		//Return Student or null depending on outcome
		return studentToReturn;
	}
	
	//Check to see if course exists
	private boolean checkCourses(String course) {
		
		//Initialize boolean to false
		boolean courseOnList = false;
		
		//Loop through list of available courses
		for (String courseFromList : courses) {
			
			//Once found, set boolean to true and break the loop
			if (courseFromList.equals(course)) {
				courseOnList = true;
				break;
			}
		}
		
		//If boolean still false, log a warning
		if (!courseOnList) logger.log(Level.WARNING, "Course does not exist.");
		
		//Return outcome of search
		return courseOnList;
	}
	
	//Check to see if student has not already enrolled on the course
	private boolean checkNotEnrolled(Student student, String course) {
		
		//Initialize a boolean to true
		boolean notEnrolled = true;
		
		//Loop through Student's list of enrolled courses, looking for the course that has been passed as an argument
		for (String enrolledCourse : student.getEnrolledCourses()) {
			
			//If found, set boolean to false and break the loop
			if (enrolledCourse.equals(course)) {
				notEnrolled = false;
				break;
			}
		}
		
		//If course has been found, log a warning
		if (!notEnrolled) logger.log(Level.WARNING, "Student already enrolled.");
		
		//Return outcome of search
		return notEnrolled;
	}
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

}
