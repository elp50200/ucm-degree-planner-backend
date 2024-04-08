package com.ucm.degreeplanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String courseCode; //Shortened course code
    private String courseName; //Full course name
    private String department; //Campus department
    private String semestersOffered; // Fall,Spring,Summer,FallSpring,FallSummer,SpringSummer,All
    private String requirementLevel; //1000,2000,3000,4000 or represented by the first number only
    private String prerequisites;  //this will be a string with classes separated by commas
}
/*
This is a domain to match the course data in the backend to be used when sending data to the frontend
 */