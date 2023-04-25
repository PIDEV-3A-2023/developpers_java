/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KHALED
 */


public class MyConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/dbecocycladb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static MyConnection instance;
    public Connection connection;
    

    private MyConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection established!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}