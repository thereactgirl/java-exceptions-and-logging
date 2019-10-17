package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{

	@Autowired
	CourseService courseService;

	@Before
	public void setUp() throws Exception
	{
		//make sure annotations are initialized
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void AfindAll()
	{
		//based off seed data
		assertEquals(6,courseService.findAll().size());
}

	@Test
	public void BgetCountStudentsInCourse()
	{
		//there are only 5 courses with students enrolled
		assertEquals(5,courseService.getCountStudentsInCourse().size());
	}

	@Test
	public void ZdeleteFound()
	{
		//TEST FOR WHEN DELETE WORKS
		courseService.delete(3L);
		assertEquals(5,courseService.findAll().size());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void ZdeleteNotFound()
	{
		//TEST FOR WHEN DELETE DOES NOT WORK
		courseService.delete(100L);
		assertEquals(5,courseService.findAll().size());

	}

	@Test
	public void CfindCourseById()
	{
		assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
	}

	@Test
	public void addNewCourse()
	{
		String coursename = "SQL";
		Instructor instructor = new Instructor("Mr. A");
		Course c3 = new Course(coursename, instructor );

		Course addcourse = courseService.save(c3);

		assertNotNull(addcourse);

		Course foundCourse = courseService.findCourseById(addcourse.getCourseid());
		assertEquals(addcourse.getCoursename(), foundCourse.getCoursename());

	}
}