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
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/degreeplanner" , "root", "root");
        System.out.println("Database connected for userDAO");

        // Create a statement
        statement = connection.createStatement();
    }
}
