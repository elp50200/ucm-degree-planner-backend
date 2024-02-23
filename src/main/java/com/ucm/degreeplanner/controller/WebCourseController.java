package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WebCourseController {


    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/request/addToSchedule")
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            //userService.create(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("There was an unexpected error with the userController/user/addUser :" + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
//
//    @PostMapping("/request/removeFromSchedule")
//    public ResponseEntity removeCourse(@RequestBody User user)
//    {
//        try{
//
//            userService.create(user);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        catch(Exception e){
//            logger.error("There was an unexpected error with the FIX THIS :" +e);
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/request/getSchedule")
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


//get schedule
//post schedule
//update schedule
//add class to schedule
//remove class from schedule
//update course(could pull entire course on frontend then update the information - longterm)
