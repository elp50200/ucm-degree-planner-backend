package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CourseDAO extends DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public CourseDAO() throws ClassNotFoundException, SQLException {
    }

    public Course getSingleCourse(String courseCode) throws Exception {
        String query = "Select * from courses where course_code = '?';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, courseCode);
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

    public ArrayList<Course> getAllCourses() throws Exception {
        String query = "Select * from courses;";
        try{
            ArrayList<Course> courses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Course course = new Course();
                course.setCourseCode(resultSet.getString("course_code"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setDepartment(resultSet.getString("department"));
                course.setPrerequisites(resultSet.getString("prerequisites"));
                course.setRequirementLevel(resultSet.getString("requirement_level"));
                course.setSemestersOffered(resultSet.getString("semesters_offered"));
                courses.add(course);
            }
            resultSet.close();
            return courses;
        }
        catch(SQLException e){
            logger.error("There was a problem in collecting all courses from the database");
            throw new Exception(e);
        }
    }

    public ArrayList<Course> getCourseLevel(int level) throws Exception {
        String query = "Select * from courses where requirement_level = '?';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, level);
        try{
            ArrayList<Course> courses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Course course = new Course();
                course.setCourseCode(resultSet.getString("course_code"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setDepartment(resultSet.getString("department"));
                course.setPrerequisites(resultSet.getString("prerequisites"));
                course.setRequirementLevel(resultSet.getString("requirement_level"));
                course.setSemestersOffered(resultSet.getString("semesters_offered"));
                courses.add(course);
            }
            resultSet.close();
            return courses;
        }
        catch(SQLException e){
            logger.error("There was a problem in collecting courses level " + level + " from the database");
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