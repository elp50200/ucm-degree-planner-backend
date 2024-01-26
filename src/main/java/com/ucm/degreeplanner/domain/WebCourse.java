package com.ucm.degreeplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebCourse {
    private String courseCode;
    private String academicSemester;
    private String studentNumber;
}
