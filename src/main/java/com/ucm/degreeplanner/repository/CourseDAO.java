package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CourseDAO extends DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public CourseDAO() throws ClassNotFoundException, SQLException {
    }

    public Course getSingleCourse(String courseCode) throws Exception {
        String query = "Select * from courses where course_code = '"+courseCode+"';";
        try{
            Course course = new Course();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                course.setCourseCode(resultSet.getString("course_code"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setDepartment(resultSet.getString("department"));
                course.setPrerequisites(resultSet.getString("prerequisites"));
                course.setRequirementLevel(resultSet.getString("requirement_level"));
                course.setSemestersOffered(resultSet.getString("semesters_offered"));
            }
            logger.info("courseCode "+courseCode+" was found");
            resultSet.close();
            return course;
        }
        catch(SQLException e){
            logger.error("There was an SQL error in retrieving course with a courseCode of  "+courseCode+" with query: "+query + "ERROR: "+e);
            throw new Exception(e);
        }
    }
}

//courseId;
//courseCode;
//courseName;
//department;
//semestersOffered;
//requirementLevel;
//prerequisites;