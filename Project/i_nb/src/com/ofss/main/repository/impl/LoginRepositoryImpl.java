package com.ofss.main.repository.impl;

import com.ofss.main.domain.Login;
import com.ofss.main.repository.LoginRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl implements LoginRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/i_nb";
    private static final String userName = "root";
    private static final String password = "Trupt!V!vek@143";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String CREATE_NEW_LOGIN = "INSERT INTO login_details(password,login_attempts,login_status) VALUES (?,0,'NEW')";
    private static final String SELECT_LOGIN_BY_CUSTOMER_ID = "SELECT * FROM login_details WHERE login_id = (SELECT login_id FROM customer_details WHERE customer_id = ?)";
    private static final String UPDATE_LOGIN_ATTEMPT_BY_ONE = "UPDATE login_details SET login_attempts=login_attempts + 1 WHERE login_id = ?";
    private static final String UPDATE_LOGIN_STATUS_BY_LOGIN_ID = "UPDATE login_details SET login_status='LOCKED' WHERE login_id = ?";

    @Override
    public int addNewLogin(Login login) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(CREATE_NEW_LOGIN, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, login.getPassword());
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int insertId = generatedKeys.getInt(1);
                    return insertId;
                }
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
        return 0;
    }

    @Override
    public Login getLoginByCustomerId(int customerId) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(SELECT_LOGIN_BY_CUSTOMER_ID);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Login login = new Login(resultSet.getInt("login_id"), resultSet.getString("password"), resultSet.getInt("login_attempts"), resultSet.getString("login_status"));
                return  login;
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
        return null;
    }

    @Override
    public boolean incrementLoginAttemptsByLoginId(int loginId) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(UPDATE_LOGIN_ATTEMPT_BY_ONE);
            preparedStatement.setInt(1, loginId);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
               return  true;
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

    @Override
    public boolean updateLoginStatusByLoginId(int loginId) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(UPDATE_LOGIN_STATUS_BY_LOGIN_ID);
            preparedStatement.setInt(1, loginId);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
               return  true;
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
