package com.pjohnson_wtc.student_db_admin;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminTest {
	
	@Mock
	Student student;
	
	@InjectMocks
	Admin admin;

	@Test
	public void testCreateStudent_createsStudent() {
		assertNotNull(admin.getAllStudents());
	}
	@Test
	public void testCreateStudent_createsStudent_withUserInput() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		assertEquals("Paddy", admin.getAllStudents().get(0).getFirstName());
	}
	@Test
	public void testCreateStudent_yearTooLow() {
		int[] studentYears = {};
		assertArrayEquals(studentYears, admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n0".getBytes())));
	}
	@Test
	public void testCreateStudent_yearTooHigh() {
		int[] studentYears = {};
		assertArrayEquals(studentYears, admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n8".getBytes())));
	}
	@Test
	public void testCreateStudent_addMultipleStudents() {
		int[] studentYears = {2, 5};
		admin.createStudent(new ByteArrayInputStream("2\nPaddy\nJohnson\n2\nMike\nRosoft\n5".getBytes()));
		assertEquals(studentYears[1], admin.getAllStudents().get(1).getStudentId() / 10000);
	}
	@Test
	public void testCreateStudent_addMultipleStudents_numberTooLow() {
		assertNull(admin.createStudent(new ByteArrayInputStream("0".getBytes())));
	}
	@Test
	public void testCreateStudent_addMultipleStudents_notRealNumber() {
		assertNull(admin.createStudent(new ByteArrayInputStream("Zero".getBytes())));
	}
	@Test
	public void testCreateStudent_yearNotNumber() {
		int[] studentYears = {};
		assertArrayEquals(studentYears, admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\neight".getBytes())));
	}
	@Test
	public void testCreateStudent_createsID() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		assertEquals(2, admin.getAllStudents().get(0).getStudentId() / 10000);
	}
	@Test
	public void testCreateStudent_alreadyInAllStudents() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		assertEquals(1, admin.getAllStudents().size());
	}
	@Test
	public void testShowStatus() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		admin.getAllStudents().get(0).getEnrolledCourses().add("History 101");
		admin.getAllStudents().get(0).getEnrolledCourses().add("Mathematics 101");
		admin.getAllStudents().get(0).setBalance(new BigDecimal(725));
		assertEquals("Paddy Johnson, " + admin.getAllStudents().get(0).getStudentId() + ", Courses enrolled: History 101, Mathematics 101, balance $725", admin.showStatus(admin.getAllStudents().get(0).getStudentId()));
	}
	@Test
	public void testShowStatus_wrongId() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		assertNull(admin.showStatus(35000));
	}
	@Test
	public void testEnrollInCourse() {
		admin.createStudent(new ByteArrayInputStream("1\nPaddy\nJohnson\n2".getBytes()));
		admin.enrollInCourse(admin.getAllStudents().get(0).getStudentId(), "History 101");
		assertEquals("History 101", admin.getAllStudents().get(0).getEnrolledCourses().get(0));
	}
}
