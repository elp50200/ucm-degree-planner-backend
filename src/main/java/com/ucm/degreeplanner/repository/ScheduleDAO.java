package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Schedule;
import com.ucm.degreeplanner.domain.WebCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ScheduleDAO extends DatabaseConnection {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public ScheduleDAO() throws ClassNotFoundException, SQLException {
    }

    public void removeFromSchedule(Schedule schedule) throws SQLException {
//        String query = "DELETE FROM schedules " +
//                "WHERE enrolled_semester = '"+schedule.getEnrolledSemester() +"' " +
//                "AND course_code = '"+schedule.getCourseCode().getCourseCode()+ "' " +
//                "AND userid = '"+schedule.getUserID().getUserID()+"';";

//        String query = "DELTE FROM schedules " +
//                "WHERE enrolled_semester = '"+ data.getAcademicSemester() +"' " +
//                "AND course_code = '"+ data.getCourseCode() + "' " +
//                "AND userid = '"+ data.getStudentNumber() +"';";

        // Your SQL query with placeholders
        String query = "DELETE FROM schedules " +
                "WHERE enrolled_semester = ? " +
                "AND course_code = ? " +
                "AND userid = ?";

        // Creating a prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Setting parameter values using the values from your Schedule object
        preparedStatement.setString(1, schedule.getEnrolledSemester());
        preparedStatement.setString(2, schedule.getCourseCode().getCourseCode());
        preparedStatement.setString(3, schedule.getUserID().getUserID().toString());

        // Executing the query
        int rowsAffected = preparedStatement.executeUpdate();

        // Checking the result
        if (rowsAffected > 0) {
            System.out.println("Delete successful.");
        } else {
            logger.error("Unable to find schedule to delete");
            throw new SQLException("Unable to find schedule to delete");
        }

        preparedStatement.close();

    }
}
//enrollmentID;
//enrolledSemester;
//student;
//course;
