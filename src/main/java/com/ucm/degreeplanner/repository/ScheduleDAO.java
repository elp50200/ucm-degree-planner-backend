package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.Schedule;
import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.domain.WebCourse;
import com.ucm.degreeplanner.service.CourseService;
import com.ucm.degreeplanner.service.UserService;
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

    public void removeFromSchedule(Schedule schedule) throws SQLException {

        // Your SQL query with placeholders
        String query = "DELETE FROM schedules " +
                "WHERE enrolled_semester = ? " +
                "AND course_code = ? " +
                "AND userid = ?";

        // Creating a prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Setting parameter values using the values from your Schedule object
        preparedStatement.setString(1, schedule.getEnrolledSemester());
        preparedStatement.setString(2, schedule.getCourse().getCourseCode());
        preparedStatement.setString(3, schedule.getUser().getStudentNumber());

        // Executing the query
        int rowsAffected = preparedStatement.executeUpdate();

        // Checking the result
        if (rowsAffected > 0) {
            logger.info("Deleted "+rowsAffected+" rows");
            System.out.println("Delete successful.");
        } else {
            logger.error("Unable to find schedule to delete");
            throw new SQLException("Unable to find schedule to delete");
        }

        preparedStatement.close();
    }

    public List<WebCourse> getSchedule(String studentNumber) throws SQLException {
        List list = new ArrayList<Schedule>();

        String query = "SELECT * FROM schedules WHERE student_number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, studentNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
        {
            WebCourse webCourse = new WebCourse();
            webCourse.setCourseCode(resultSet.getString("course_code"));
            webCourse.setStudentNumber(resultSet.getString("student_number"));
            webCourse.setAcademicSemester(resultSet.getString("enrolled_semester"));
            list.add(webCourse);
            //ChangeRequest request = new ChangeRequest();
            //                request.setChangeNumber(resultSet.getInt("change_number"));
        }
        return list;
    }

    public void add(Schedule schedule) throws SQLException {
        String query = "INSERT into schedules (enrolled_semester,course_code,student_number) VALUES (?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, schedule.getEnrolledSemester());
        preparedStatement.setString(2, schedule.getCourse().getCourseCode());
        preparedStatement.setString(3, schedule.getUser().getStudentNumber());

        preparedStatement.execute();

    }
}

//enrolled_semester;
//student_number;
//course_code;
