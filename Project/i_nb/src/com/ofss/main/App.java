package com.ofss.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
       System.out.println("main start");

        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/i_nb";
        String userName = "root";
        String password = "Trupt!V!vek@143";
        Connection connection = null;
        try {
            //1. Load JDBC Driver
            Class.forName(driverName);
            connection= DriverManager.getConnection(url,userName , password);

            if(connection!= null){
                System.out.println("Connection Successfull");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect database");
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
               System.out.println("Failed to close connection");
                e.printStackTrace();
            }
        }

        System.out.println("main end");

    }
}
