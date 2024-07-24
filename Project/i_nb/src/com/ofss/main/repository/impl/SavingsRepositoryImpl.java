package com.ofss.main.repository.impl;

import com.ofss.main.domain.Savings;
import com.ofss.main.repository.SavingsRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavingsRepositoryImpl implements SavingsRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/i_nb";
    private static final String userName = "root";
    // private static final String password = "Trupt!V!vek@143";
    private static final String password = "root";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String CREATE_NEW_SAVINGS_ACCOUNT = "INSERT INTO savings_account_details(savings_account_id) VALUES(?)";

    @Override
    public boolean addNewSavingsAccount(Savings savings) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(CREATE_NEW_SAVINGS_ACCOUNT);
            preparedStatement.setInt(1, savings.getAccountId());
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect database");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection");
                e.printStackTrace();
            }
        }
        return false;
    }

}
