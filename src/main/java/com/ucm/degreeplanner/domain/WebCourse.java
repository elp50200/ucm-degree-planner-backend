package com.ucm.degreeplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebCourse {
    private String courseCode; //course code of the course that needs to be enrolled
    private String academicSemester; //academic semester that student wants to enroll in
    private String studentNumber; //student number of the student to be enrolled
}
/*
This domain is a singleton version of BulkSchedule and is used as an object for the frontend to match
It is mostly unused in the actual app and is kept for future use of adding/removing single courses as necessary
 */