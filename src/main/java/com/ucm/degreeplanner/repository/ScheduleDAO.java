package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Schedule;
import com.ucm.degreeplanner.domain.WebCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDAO extends DatabaseConnection {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public ScheduleDAO() throws ClassNotFoundException, SQLException {
    }

    /*
    This is a SQL call using a prepared statement that deletes all schedule rows that are ties to a student and course
    */
    public void removeFromSchedule(Schedule schedule) throws SQLException {

        String query = "DELETE FROM schedules " +
                "WHERE course_code = ? " +
                "AND student_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, schedule.getCourse().getCourseCode());
        preparedStatement.setString(2, schedule.getUser().getStudentNumber());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            logger.info("Deleted "+rowsAffected+" rows");
        } else {
            logger.info("Unable to find schedule to delete");
            throw new SQLException("Unable to find schedule to delete");
        }
        preparedStatement.close();
    }
    /*
    This is a SQL call using a prepared statement that gets all schedule rows that are related to a single student number
    */
    public List<WebCourse> getSchedule(String studentNumber) throws SQLException {
        List list = new ArrayList<Schedule>();

        String query = "SELECT * FROM schedules WHERE student_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, studentNumber);

        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WebCourse webCourse = new WebCourse();
                webCourse.setCourseCode(resultSet.getString("course_code"));
                webCourse.setStudentNumber(resultSet.getString("student_number"));
                webCourse.setAcademicSemester(resultSet.getString("enrolled_semester"));
                list.add(webCourse);
            }
            logger.info("All schedules for student "+studentNumber+" have been pulled");
            preparedStatement.close();
            return list;
        }
        catch(Exception e){
            preparedStatement.close();
            logger.info("There was a SQL error in getting the schedules for student "+studentNumber+" with query "+query + "ERROR: "+e);
            throw new SQLException(e);
        }
    }

    /*
    This is a SQL call using a prepared statement that inserts a new schedule row into the schedule table properly
     */
    public void add(Schedule schedule) throws SQLException {
        String query = "INSERT into schedules (enrolled_semester,course_code,student_number) VALUES (?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, schedule.getEnrolledSemester());
        preparedStatement.setString(2, schedule.getCourse().getCourseCode());
        preparedStatement.setString(3, schedule.getUser().getStudentNumber());

        try{
            preparedStatement.execute();
        }
        catch(SQLException e){
            logger.info("There was an error adding a course to a students schedule");
            throw new SQLException(e);
        }
    }

    /*
    This is a SQL call using a prepared statement that checks if a course exist and returns a boolean value
    */
    public boolean courseExist(Schedule schedule) throws SQLException {
        Boolean courseExist = false;

        String query = "SELECT * FROM schedules WHERE course_code = ? AND student_number = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, schedule.getCourse().getCourseCode());
        preparedStatement.setString(2, schedule.getUser().getStudentNumber());

        try{
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                courseExist = true;
            }

            return courseExist;
        }
        catch(SQLException e){
            logger.info("There was an error in checking if a course exists");
            throw new SQLException(e);
        }
    }
}