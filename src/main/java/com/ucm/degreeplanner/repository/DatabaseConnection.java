package com.ucm.degreeplanner.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public Connection connection = DriverManager.getConnection
            ("jdbc:mysql://localhost/degreeplanner" , "root", "root");;
    public Statement statement = connection.createStatement();

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
    }
}
//This is a very rudimentary class to create connections for the DAO classes to use to make database calls