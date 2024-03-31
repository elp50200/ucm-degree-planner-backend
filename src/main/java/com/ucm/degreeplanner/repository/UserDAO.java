package com.ucm.degreeplanner.repository;

import com.ucm.degreeplanner.domain.User;
import com.ucm.degreeplanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDAO extends DatabaseConnection{

//    private Statement statement;
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO() throws ClassNotFoundException, SQLException {
    }

    //    public UserDAO() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //System.out.println("Driver loaded");
//
//        // Connect to a database
//        Connection connection = DriverManager.getConnection
//                ("jdbc:mysql://localhost/degreeplanner" , "root", "root");
//        //System.out.println("Database connected for userDAO");
//
//        // Create a statement
//        statement = connection.createStatement();
//    }
    public boolean updateUser(User user){
        String query = " Update users set " +
                "username = '"+user.getUsername()+"' " +
                ",password = '"+user.getPassword()+"' "+
                ",email_address = '"+user.getEmailAddress()+"' "+
                ",fname= '"+user.getFname()+"' "+
                ",lname = '"+user.getLname()+"' "+
                ",role = '"+user.getRole()+"' "+
                ",catalog_year = '"+user.getCatalogYear()+"' "+
                "where student_number = '"+user.getStudentNumber()+"';";
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
//userID;
//username;
//password;
//emailAddress;
//studentNumber;
//fname;
//lname;
//role;
//catalogYear;
