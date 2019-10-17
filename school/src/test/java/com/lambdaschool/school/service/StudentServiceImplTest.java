package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class StudentServiceImplTest
{
	@Autowired
	private StudentService studentService;

	//this runs before test runs
	@Before
	public void setUp() throws Exception
	{
		//make sure annotations are initialized
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception
	{
		//java is handling my tear down
	}

	@Test
	public void findAll()
	{
		//based off seed data
		assertEquals(13,studentService.findAll().size());
	}

	@Test
	public void findStudentById()
	{
		assertEquals("John", studentService.findStudentById(1).getStudname());
	}

	@Test
	public void findStudentByNameLike()
	{
	}

	@Test
	public void delete()
	{
	}

	@Test
	public void save()
	{
	}

	@Test
	public void update()
	{
		//make a student
		Student s1 = new Student("Leslie");
//		s1.setStudid(15L);
		Student updatedS1 = studentService.update(s1, 15L);

		assertEquals("Leslie", updatedS1.getStudname());
	}
}