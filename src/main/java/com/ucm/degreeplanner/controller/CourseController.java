package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.Course;
import com.ucm.degreeplanner.service.CourseService;
import com.ucm.degreeplanner.service.WebCourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final CourseService courseService;
    private final WebCourseService webCourseService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/course/addCourse")
    public ResponseEntity addCourse(@RequestBody Course course)
    {
        try{
            courseService.addCourse(course);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch(Exception e){
            logger.error("There was an error with course/addCourse :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PostMapping("/course/removeCourse")
//    public ResponseEntity removeCourse(@RequestBody)
//    {
//        try{
//            return new ResponseEntity();
//        }
//        catch(Exception e){
//            logger.error("There was an unexpected error with the FIX THIS :" +e);
//            return new ResponseEntity();
//        }
//    }
//
    @PostMapping("/course/getCourse/{courseCode}")
    public ResponseEntity removeCourse(@PathVariable String courseCode)
    {
        try{
            Course course = courseService.getSingleCourse(courseCode);
            return new ResponseEntity(course, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("There was an error with course/getCourse/{courseCode} :" +e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
//
//    @PostMapping("/course/getAllCourse")
//    public ResponseEntity removeCourse(@RequestBody)
//    {
//        try{
//            return new ResponseEntity();
//        }
//        catch(Exception e){
//            logger.error("There was an error with the FIX THIS :" +e);
//            return new ResponseEntity();
//        }
//    }
//
//    @PostMapping("/course/getCourseOfYear/{year}")
//    public ResponseEntity removeCourse(@PathVariable String year)
//    {
//        try{
//            return new ResponseEntity();
//        }
//        catch(Exception e){
//            logger.error("There was an unexpected error with the FIX THIS :" +e);
//            return new ResponseEntity();
//        }
//    }
}

//get available classes based on year
//get single class
//get all classes
//get schedule
//post schedule
//update schedule
//add class to schedule
//remove class from schedule
//update course(could pull entire course on frontend then update the information - longterm)
