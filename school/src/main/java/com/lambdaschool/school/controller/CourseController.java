package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    @Autowired
    private CourseService courseService;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    //http://localhost:2019/courses/courses/?page=1&size=3
    @ApiOperation(value = "list all courses", response = Course.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/courses/paging",
                produces = {"application/json"})
    public ResponseEntity<?> listsAllCoursesByPage(@PageableDefault(page = 0, size = 3) Pageable pageable)
    {
        List<Course> myCourses = courseService.findAllPageable(pageable);
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }


    //lists all courses
    @ApiOperation(value = "list all courses", response = Course.class, responseContainer = "List")
    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses(HttpServletRequest request)
    {
        logger.warn("This is a log");
        logger.trace("This is another log");
        logger.info(request.getMethod() + " " + request.getRequestURI() + " ACCESSED");
        ArrayList<Course> myCourses = courseService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @ApiOperation(value = "list all courses and how many students in each course", response = Course.class, responseContainer = "List")
    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses(HttpServletRequest request)
    {

        logger.info(request.getMethod() + " " + request.getRequestURI());
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes A course")
    @DeleteMapping("/courses/{courseid}")
    @ApiResponses(value = {@ApiResponse(code = 200,
                                        message = "Course deleted"),
    @ApiResponse(code=404, message = "Course Id not found", response = ErrorDetail.class)})
    public ResponseEntity<?> deleteCourseById(
            @ApiParam(value = "Id of COURSE", required = true, example = "1")
            @PathVariable long courseid)
    {
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/student/{courseid}",
            produces = {"application/json"})
    public ResponseEntity<?> getCourseById(
            @PathVariable
                    Long courseid)
    {
        Course c = courseService.findCourseById(courseid);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping(value = "/course/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCourse(@Valid
                                           @RequestBody
                                                   Course newCourse) throws URISyntaxException
    {
        newCourse = courseService.save(newCourse);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseid}").buildAndExpand(newCourse.getCourseid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
