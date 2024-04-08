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
            logger.error("There was an unexpected error with the /user/addUser :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/checkUserLogin")
    public ResponseEntity checkUserLogin(@RequestBody User user){
        try{
            boolean validLogin = userService.checkUserLogin(user.getUsername(), user.getPassword());

            if (validLogin) {
                //TODO Rework this logic. I hate it
                User goodUser = userService.getUserByUsername(user.getUsername());
                goodUser.setPassword(null);
                goodUser.setSalt(null);
                return new ResponseEntity(goodUser, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            logger.error("There was an unexpected error with the /user/checkUserLogin :" +e);
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
            logger.error("There was an unexpected error with the /user/updateUser :" +e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
