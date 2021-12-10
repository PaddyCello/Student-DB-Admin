package com.pjohnson_wtc.student_db_admin;

import static org.junit.Assert.*;

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
	public void testCreateStudent() {
		assertEquals(6, admin.createStudent());
	}

}
