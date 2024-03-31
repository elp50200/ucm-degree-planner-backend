package com.ucm.degreeplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulkSchedule {
    private String[] courses;
    private String academicSemester;
    private String studentNumber;
}
