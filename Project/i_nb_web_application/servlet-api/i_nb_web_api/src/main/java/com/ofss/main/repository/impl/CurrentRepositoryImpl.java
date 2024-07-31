package com.ofss.main.repository.impl;

import com.ofss.main.domain.Current;
import com.ofss.main.repository.CurrentRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentRepositoryImpl implements CurrentRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/i_nb";
    private static final String userName = "root";
    // private static final String password = "Trupt!V!vek@143";
    private static final String password = "root";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String CREATE_NEW_CURRENT_ACCOUNT = "INSERT INTO current_account_details(current_account_id,overdraft_balance,remaining_overdraft_balance) VALUES(?,?,?);";

    @Override
    public boolean addNewCurrentAccount(Current current) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(CREATE_NEW_CURRENT_ACCOUNT);
            preparedStatement.setInt(1, current.getAccountId());
            preparedStatement.setDouble(2, current.getOverdraftBalance());
            preparedStatement.setDouble(3, current.getRemainingOverdraftBalance());
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
