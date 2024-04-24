package com.ucm.degreeplanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String studentNumber; // student 700 number
    private String username; //self assigned username - used for login
    private String password; //User's password
    private String emailAddress; //User's email
    private String fname; //First name
    private String lname; //Last name
    private String role; //Role of student or staff (student,staff,admin)
    private String catalogYear; //catalog year the student is enrolled in
    private byte[] salt; //Randomized salt for hashing password
}
/*
This is the user domain and matches the backend table stored
 */