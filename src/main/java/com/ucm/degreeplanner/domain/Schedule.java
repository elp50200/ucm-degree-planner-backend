package com.ucm.degreeplanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollmentID; //Auto incremented number for the enrollmentID
    private String enrolledSemester; //Semester and year the student is enrolled in (ie 202410)
    private Date lastUpdated; // Day of last update. Primarily used to clear database for new schedules
    @ManyToOne
    @JoinColumn(name = "studentNumber")
    private User user; // The user that this is enrolled
    @ManyToOne
    @JoinColumn(name = "courseCode")
    private Course course; // The course the user is enrolled in
}
/*
This is a domain that matches the backend table for schedule storage. Can be used to get all applicable information of the schedule
 */