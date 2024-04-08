package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDAO extends DatabaseConnection{

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO() throws ClassNotFoundException, SQLException {
    }
    /*
    This is a SQL call using a prepared statement that updates all user information for one student
    */
    public boolean updateUser(User user) throws SQLException {
        String query = " Update users set " + "username = ?, password = ?, email_address = ?, "
                + "fname= ?, lname = ?, role = ?, catalog_year = ? where student_number = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmailAddress());
        preparedStatement.setString(4, user.getFname());
        preparedStatement.setString(5, user.getLname());
        preparedStatement.setString(6, user.getRole());
        preparedStatement.setString(7, user.getCatalogYear());
        preparedStatement.setString(8, user.getStudentNumber());
        try{
            statement.executeUpdate(query);
            logger.info("User "+user.getStudentNumber()+" was updated");
            return true;
        }
        catch(SQLException e){
            logger.error("There was an SQL error in updating User "+user.getStudentNumber()+" with query: "+query + "ERROR: "+e);
            return false;
        }
    }
}