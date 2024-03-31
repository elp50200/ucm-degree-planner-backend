package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.BulkSchedule;
import com.ucm.degreeplanner.domain.Schedule;
import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.domain.WebCourse;
import com.ucm.degreeplanner.service.CourseService;
import com.ucm.degreeplanner.service.ScheduleService;
import com.ucm.degreeplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WebCourseController {
    private final ScheduleService scheduleService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/request/addToSchedule")
    public ResponseEntity addToSchedule(@RequestBody WebCourse enrollmentData) {
        try {
            scheduleService.addToSchedule(enrollmentData);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("There was an error with /request/addToSchedule :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/request/removeFromSchedule")
    public ResponseEntity removeFromSchedule(@RequestBody WebCourse data) {
        try {
            scheduleService.removeFromSchedule(data);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("There was an error with /request/removeFromSchedule :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/request/getSchedule/{studentNumber}")
    public ResponseEntity getSchedule(@PathVariable String studentNumber) {
        try {
            List list = scheduleService.getSchedule(studentNumber);
            return new ResponseEntity(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("There was an unexpected error with the FIX THIS :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/request/BulkSchedule")
    public ResponseEntity bulkSchedule(@RequestBody BulkSchedule enrollmentData) {
        try {
            scheduleService.bulkSchedule(enrollmentData);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("There was an error with /request/addToSchedule :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PostMapping("/request/setSchedule")
//    public ResponseEntity removeCourse(@RequestBody User user)
//    {
//        try{
//            userService.create(user);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        catch(Exception e){
//            logger.error("There was an unexpected error with the FIX THIS :" +e);
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/request/updateSchedule")
//    public ResponseEntity removeCourse(@RequestBody User user)
//    {
//        try{
//            userService.create(user);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        catch(Exception e){
//            logger.error("There was an unexpected error with the FIX THIS :" +e);
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
}

//get schedule
//post schedule
//update schedule
//add class to schedule
//remove class from schedule
//update course(could pull entire course on frontend then update the information - longterm)
