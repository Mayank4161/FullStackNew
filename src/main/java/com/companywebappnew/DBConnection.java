package com.companywebappnew;

import java.sql.*;
import java.util.*;
import java.io.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
        if (in == null) {
            System.err.println("Error: db.properties file not found in classpath.");
            throw new FileNotFoundException("db.properties file not found in classpath.");
        }
        props.load(in);
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found. Please add it to your project dependencies.");
            throw e;
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error: Could not connect to database. Check your db.properties and database server.");
            throw e;
        }
    }
} 