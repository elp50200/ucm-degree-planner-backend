package com.ucm.degreeplanner.controller;

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//this is just a placeholder file
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @PostMapping("/user/addUser")
    public ResponseEntity addUser(@RequestBody User user)
    {
        try{
            userService.create(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch(Exception e){
            //log failure here
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

//    @CrossOrigin
//    @PostMapping("/addUser")
//    public ResponseEntity<?> save(@RequestBody CMuser user)
//    {
//        if(user.getUserID()!=null&&user.getPassword()!=null) {
//            return new ResponseEntity<>(cmUserService.create(user), HttpStatus.CREATED);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/checkUserLogin")
//    public ResponseEntity<?> checkUserLogin(@RequestBody CMuser user ) {
//        boolean isGoodLogin = cmUserService.isGoodLogin(user.getUserID(), user.getPassword());
//
//        if (isGoodLogin) {
//            CMuser goodUser = cmUserService.getUser(user.getUserID());
//            return new ResponseEntity(goodUser, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }
//    private final ChangeRequestService changeRequestService;
//    @CrossOrigin
//    @PostMapping("/addChangeRequest/{userID}")
//    public ResponseEntity<?> change(@RequestBody ChangeRequest changeRequest, @PathVariable String userID)
//    {
//        changeRequest.setStatus("frozen");
//        return new ResponseEntity<>(changeRequestService.create(changeRequest, userID), HttpStatus.CREATED);
//    }
//    @CrossOrigin
//    @GetMapping("/getChangeRequest/{userID}")
//    public ResponseEntity<List<ChangeRequest>> getViewableRequest(@PathVariable String userID)
//    {
//        return new ResponseEntity<List<ChangeRequest>>(changeRequestService.getViewableRequest(userID), HttpStatus.OK);
//    }
//    @CrossOrigin
//    @GetMapping("/viewOneChangeRequest/{changeNumber}")
//    public ResponseEntity<?> viewOneRequest(@PathVariable String changeNumber, String column, String value) {
//
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            return new ResponseEntity<>(changeRequestService.viewOneChangeRequest(cNumber), HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateStatus/{changeNumber}/status/{status}")
//    public ResponseEntity<?> updateStatus(@PathVariable String changeNumber, @PathVariable String status){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            changeRequestService.updateStatusWithStatus(cNumber,status);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateStatus/{changeNumber}/approved")
//    public ResponseEntity<?> updateStatusApproved(@PathVariable String changeNumber){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            changeRequestService.updateStatusApproved(cNumber);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateStatus/{changeNumber}/denied")
//    public ResponseEntity<?> updateStatusDenied(@PathVariable String changeNumber){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            changeRequestService.updateStatusDenied(cNumber);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateImplStart/changeNumber/{changeNumber}/startTime/{startTime}")
//    public ResponseEntity<?> updateImplStart(@PathVariable String changeNumber, @PathVariable String startTime){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//
//            //validate format for startTime YYYY-MM-DD
//            startTime = LocalDate.parse(startTime).toString();
//
//            //update time for a ChangeRequest Object
//            changeRequestService.updateImplStart(cNumber, startTime);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateImplementor/changeNumber/{changeNumber}/implementor/{implementorName}")
//    public ResponseEntity<?> updateImplementor(@PathVariable String changeNumber, @PathVariable String implementorName){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            //update implementor for a ChangeRequest Object
//            changeRequestService.updateImplementor(cNumber, implementorName);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateImplementationStatus/changeNumber/{changeNumber}/success")
//    public ResponseEntity<?> updateImplementationSuccess(@PathVariable String changeNumber){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            //update implementor for a ChangeRequest Object
//            changeRequestService.updateImplementation(cNumber, "success");
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/updateImplementationStatus/changeNumber/{changeNumber}/failure")
//    public ResponseEntity<?> updateImplementationFailure(@PathVariable String changeNumber){
//        try {
//            int cNumber = Integer.parseInt(changeNumber);
//            //update implementor for a ChangeRequest Object
//            changeRequestService.updateImplementation(cNumber, "failure");
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NumberFormatException e) {
//            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
//        }
//    }
//
//    @CrossOrigin
//    @PostMapping
//    public ResponseEntity<?> cleanRequest(){
//        try {
//            changeRequestService.cleanRequest();
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//    }
//
//    @CrossOrigin
//    @GetMapping("/getCompletedRequest")
//    public ResponseEntity<List<ChangeRequest>> getCompletedRequests()
//    {
//        return new ResponseEntity<List<ChangeRequest>>(changeRequestService.getCompletedRequest(), HttpStatus.OK);
//    }

}
