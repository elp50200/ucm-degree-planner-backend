package com.ucm.degreeplanner.service;
//this is just a placeholder file

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.repository.UserDAO;
import com.ucm.degreeplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDAO userDAO;

    // Create a logger for the class
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public User create(User user){
        user.setRole(user.getRole().toLowerCase());
        return userRepository.save(user);
    }

    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User getUserByStudentNumber(String studentNumber) {
        return userRepository.findByStudentNumber(studentNumber);
    }

    public boolean checkUserLogin(String username, String password) throws Exception{
        try
        {
            //sanitize data and check to see if the username and password do not contain dangerous data
            if( (password.equals(preventSQLInjection(password))) && (username.equals(preventSQLInjection(username)))){
                logger.info("Strings are valid and do not attempt to inject the database");
            }
            else{
                throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() +" and the process checkUserLogin has been stopped.");
            }


            User user = userRepository.findByUsername(username);
            if (password.equals(user.getPassword())) {
                logger.info("User: " + username + " has been found and password matches");
                return true;
            } else {
                logger.info("Username or Password are incorrect.");
                return false;
            }
        }
        catch (Exception e){
            logger.error("There was an error in finding the username and password due to an invalid character: "+e);
            return false;
        }
    }

    public boolean updateUserInformation(User userUpdates) throws Exception{
        try {
            //pull in current data for user
            User userCurrent = userRepository.findByStudentNumber(userUpdates.getStudentNumber());

            // Username
            if(userUpdates.getUsername() != null){
                String sanitizedUsername = preventSQLInjection(userUpdates.getUsername());
                if(userUpdates.getUsername().equals(sanitizedUsername)) {
                    userCurrent.setUsername(userUpdates.getUsername());
                }
                else{
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() +" and the process updateUserInformation for username has been stopped.");
                }
            }
            // Password
            if(userUpdates.getPassword() != null){
                String sanitizedPassword = preventSQLInjection(userUpdates.getPassword());
                if(userUpdates.getPassword().equals(sanitizedPassword)) {
                    userCurrent.setPassword(userUpdates.getPassword());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for password has been stopped.");
                }
            }

            // Email Address
            if(userUpdates.getEmailAddress() != null){
                String sanitizedEmail = preventSQLInjectionEmail(userUpdates.getEmailAddress());
                if(userUpdates.getEmailAddress().equals(sanitizedEmail)) {
                    userCurrent.setEmailAddress(userUpdates.getEmailAddress());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for email address has been stopped.");
                }
            }

            // Student Number
//            if(userUpdates.getStudentNumber() != null){
//                String sanitizedStudentNumber = preventSQLInjection(userUpdates.getStudentNumber());
//                if(userUpdates.getStudentNumber().equals(sanitizedStudentNumber)) {
//                    userCurrent.setStudentNumber(userUpdates.getStudentNumber());
//                } else {
//                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for student number has been stopped.");
//                }
//            }

            // fname
            if(userUpdates.getFname() != null){
                String sanitizedFname = preventSQLInjection(userUpdates.getFname());
                if(userUpdates.getFname().equals(sanitizedFname)) {
                    userCurrent.setFname(userUpdates.getFname());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for first name has been stopped.");
                }
            }

            // lname
            if(userUpdates.getLname() != null){
                String sanitizedLname = preventSQLInjection(userUpdates.getLname());
                if(userUpdates.getLname().equals(sanitizedLname)) {
                    userCurrent.setLname(userUpdates.getLname());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for last name has been stopped.");
                }
            }

            // Role
            if(userUpdates.getRole() != null){
                String sanitizedRole = preventSQLInjection(userUpdates.getRole());
                if(userUpdates.getRole().equals(sanitizedRole)) {
                    userCurrent.setRole(userUpdates.getRole());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for role has been stopped.");
                }
            }

            // Catalog Year
            if(userUpdates.getCatalogYear() != null){
                String sanitizedCatalogYear = preventSQLInjection(userUpdates.getCatalogYear());
                if(userUpdates.getCatalogYear().equals(sanitizedCatalogYear)) {
                    userCurrent.setCatalogYear(userUpdates.getCatalogYear());
                } else {
                    throw new Exception("A invalid/dangerous character has been detected on " + LocalDate.now() + " and the process updateUserInformation for catalog year has been stopped.");
                }
            }

            //send to DAO
            boolean result = userDAO.updateUser(userCurrent);
            return result;
        }
        catch(Exception e){
            logger.error("There is an error in updating User "+userUpdates.getStudentNumber()+" due to an illegal character: "+e);
            return false;
        }
    }

    private String preventSQLInjection(String input) {
        // Define a regular expression pattern to match potentially harmful characters
        String regex = "[;\\\\/'\"()|&%#@<>]";

        // Remove characters matching the pattern
        String sanitizedInput = input.replaceAll(regex, "XXX");

        return sanitizedInput;
    }
    private String preventSQLInjectionEmail(String input) {
        // Define a regular expression pattern to match potentially harmful characters
        String regex = "[;\\\\/'\"()|&%#<>]";

        // Remove characters matching the pattern
        String sanitizedInput = input.replaceAll(regex, "XXX");

        return sanitizedInput;
    }
}
