package com.ucm.degreeplanner.service;
//this is just a placeholder file

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    public User create(User user){
        return userRepository.save(user);
    }

    public User getUser(String userID)
    {
        return userRepository.findByUsername(userID);
    }

    public boolean isGoodLogin(String username, String password){
        try
        {
            if(!password.equals(preventSQLInjection(password))){
                return false;
            }


            User user = userRepository.findByUsername(username);
            if (password.equals(user.getPassword())) {
                System.out.println("User: " + username + " has been found and password matches");
                return true;
            } else {
                System.out.println("Username or Password are incorrect.");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Error in finding or evaluating userid and password");
            return false;
        }
    }
    public static String preventSQLInjection(String input) {
        // Define a regular expression pattern to match potentially harmful characters
        String regex = "[;\\\\/'\"()|&%#@<>]";

        // Remove characters matching the pattern
        String sanitizedInput = input.replaceAll(regex, "");

        return sanitizedInput;
    }
}
