package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.BulkSchedule;
import com.ucm.degreeplanner.domain.WebCourse;
import com.ucm.degreeplanner.service.ScheduleService;
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
            logger.error("There was an unexpected error with /request/getSchedule/{studentNumber} :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/request/bulkSchedule/semester/{startSemester}")
    public ResponseEntity bulkSchedule(@RequestBody BulkSchedule enrollmentData, @PathVariable String startSemester) {
        try {
            scheduleService.bulkSchedule(enrollmentData, startSemester);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("There was an error with /request/bulkSchedule/semester/{startSemester} :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}