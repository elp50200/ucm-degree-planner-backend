package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/addUser")
    public ResponseEntity addUser(@RequestBody User user)
    {
        try{
            userService.create(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch(Exception e){
            logger.error("There was an unexpected error with the userController/user/addUser :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/checkUserLogin")
    public ResponseEntity checkUserLogin(@RequestBody User user){
        try{
            boolean validLogin = userService.checkUserLogin(user.getUsername(), user.getPassword());

            if (validLogin) {
                User goodUser = userService.getUser(user.getUsername());
                return new ResponseEntity(goodUser, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            logger.error("There was an unexpected error with the userController/user/checkUserLogin :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/updateUser")
    public ResponseEntity updateUserInformation(@RequestBody User user){
        try{
            boolean result = userService.updateUserInformation(user);
            if(result){
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            logger.error("There was an unexpected error with the userController/user/updateUser :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
