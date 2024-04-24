package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.Course;
import com.ucm.degreeplanner.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final CourseService courseService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/course/addCourse")
    public ResponseEntity addCourse(@RequestBody Course course)
    {
        try{
            courseService.addCourse(course);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch(Exception e){
            logger.error("There was an error with /course/addCourse :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/course/removeCourse/{courseCode}")
    public ResponseEntity removeCourse(@PathVariable String courseCode)
    {
        try{
            courseService.removeCourse(courseCode);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("There was an unexpected error with /course/removeCourse/{courseCode} :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/course/getCourse/{courseCode}")
    public ResponseEntity getSingleCourse(@PathVariable String courseCode)
    {
        try{
            Course course = courseService.getSingleCourse(courseCode);
            return new ResponseEntity(course, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("There was an error with /course/getCourse/{courseCode} :" +e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course/getAllCourses")
    public ResponseEntity getAllCourses()
    {
        try{
            ArrayList<Course> courses = courseService.getAllCourses();
            return new ResponseEntity(courses,HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("There was an error with /course/getAllCourse :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/course/getCourseLevel/{level}")
    public ResponseEntity getCourseLevel(@PathVariable String level)
    {
        try{
            ArrayList<Course> courses = courseService.getCourseLevel(level);
            return new ResponseEntity(courses,HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("There was an unexpected error with /course/getCourseLevel/{level} :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}