package com.ucm.degreeplanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollmentID;
    private String enrolledSemester;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;
    @ManyToOne
    @JoinColumn(name = "courseCode")
    private Course courseCode;
}
