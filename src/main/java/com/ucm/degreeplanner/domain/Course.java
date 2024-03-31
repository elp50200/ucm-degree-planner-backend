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
    private String courseCode;
    private String courseName; //full actual course name
    private String department;
    private String semestersOffered; // fall,spring,summer,fallspring,fallsummer,springsummer,all
    private String requirementLevel; //1000,2000,3000,4000 or by expected student year. Not sure yet
    private String prerequisites;  //this will be a string with classes separated by commas


//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<ChangeRequest> ChangeRequests = new ArrayList<>();

}