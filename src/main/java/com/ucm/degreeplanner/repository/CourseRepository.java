package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseByCourseCode(String courseCode);
}
