package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //randomly finds an open port
public class CourseControllerIntegrationTest
{
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup()
	{
		//this set ups the environment
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	//    GET /courses/courses
	@Test
	public void whenMeasuredReponseTime()
	{
		given().when().get("/courses/courses").then().time(lessThan(5000L));

	}


	//    POST /courses/course/add
	@Test
	public void givenPostACourse() throws Exception
	{
		String coursename = "SQL";
		Instructor instructor = new Instructor("Mr. A");
		Course c3 = new Course(coursename, instructor );

		//conversion of object to json
		ObjectMapper mapper = new ObjectMapper();
		String stringC3 = mapper.writeValueAsString(c3);

		given().contentType("application/json").body(stringC3).when().post("/courses/course/add").then().statusCode(201);
	}
}
