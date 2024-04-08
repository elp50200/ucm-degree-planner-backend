package com.ucm.degreeplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulkSchedule {
    private String[] courses; //This is the array of course codes
    private String semesterID; //This is the index of the semester the courses need to be enrolled in
    private String studentNumber; //This is the student number of the student whos schedule needs to be updated
}
/*
This domain object is the main object used by the front-end to send semester enrollment data to the backend.
 */
