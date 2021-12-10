package com.pjohnson_wtc.student_db_admin;

import java.util.Scanner;

public class Admin {
	
	public int createStudent() {
		// TODO Auto-generated method stub
		return 6;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is your name?");
		
		String name = scanner.nextLine();
		scanner.close();
		
		System.out.println("Nice to meet you, " + name);

	}

}
