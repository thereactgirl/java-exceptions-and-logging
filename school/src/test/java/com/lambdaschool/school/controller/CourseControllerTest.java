package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest
{
	//mock web environment to work with
	@Autowired
	private MockMvc mockMvc;

	//this doesn't call the actual course service
	@MockBean
	private CourseService courseService;

	//everything is an arraylist
	//we need this because we don't actually test h2, we just mock the server
	private ArrayList<Course> courseList;


	@Before
	public void setUp() throws Exception
	{
		//MAKE A LIST FOR COURSES
		courseList = new ArrayList<>();

		//MAKE INSTRUCTORS
		Instructor i1 = new Instructor("Sally");
		Instructor i2 = new Instructor("Lucy");
		Instructor i3 = new Instructor("Charlie");

		//GIVE ID'S TO INSTRUCTORS
		i1.setInstructid(1);
		i2.setInstructid(2);
		i3.setInstructid(3);

		//MAKE A STUDENT LIST
		ArrayList<Student> students = new ArrayList<>();

		//MAKE THE STUDENTS
		Student s1 = new Student("John");
		Student s2 = new Student("Julian");
		Student s3 = new Student("Mary");

		//GIVE IDS TO STUDENTS
		s1.setStudid(1);
		s2.setStudid(2);
		s3.setStudid(3);

		//MAKE COURSES
		Course c1 = new Course("Data Science", i1);
		Course c2 = new Course("JavaScript", i1);
		Course c3 = new Course("Node.js", i1);
		Course c4 = new Course("Java Back End", i2);

		//GIVE IDS, INSTRUCTORS, AND STUDENTS TO COURSES
		c1.setCourseid(1);
		c1.setInstructor(i1);
		c1.setStudents(students);

		c2.setCourseid(2);
		c2.setInstructor(i2);
		c2.setStudents(students);

		c3.setCourseid(3);
		c3.setInstructor(i3);
		c3.setStudents(students);

		c4.setCourseid(4);
		c4.setInstructor(i2);
		c4.setStudents(students);

		//ADD COURSES TO COURSE LIST
		courseList.add(c1);
		courseList.add(c2);
		courseList.add(c3);
		courseList.add(c4);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void listAllCourses() throws Exception
	{
//		//specific endpoint i need
//		String apiurl = "/courses/courses";
//
//		//calls the mocked version of the service
//		Mockito.when(courseService.findAll()).thenReturn(courseList);
//
//		RequestBuilder rb = MockMvcRequestBuilders.get(apiurl).accept(MediaType.APPLICATION_JSON);
//
//		// the following actually performs a real controller call
//		MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//		String tr = r.getResponse().getContentAsString(); //gets the res and returns as a string
//
//		//manual conversion of string to json
//		ObjectMapper mapper = new ObjectMapper();
//		String er = mapper.writeValueAsString(courseList);
//
//		assertEquals("Rest API Returns List", er, tr);

	}

	@Test
	public void getCountStudentsInCourses()
	{
	}

	@Test
	public void deleteCourseById()
	{
	}
}