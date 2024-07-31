package com.ofss.main.repository.impl;

import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.repository.CustomerRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/i_nb";
    private static final String userName = "root";
    // private static final String password = "Trupt!V!vek@143";
    private static final String password = "root";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String CREATE_NEW_CUSTOMER = "INSERT INTO customer_details(first_name,last_name,gender,email,mobile,login_id,customer_status) VALUES(?,?,?,?,?,?,'NEW')";
    private static final String SELECT_ONE_CUSTOMER = "SELECT * FROM customer_details WHERE customer_id = ?";

    @Override
    public int addNewCustomer(Customer customer) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(CREATE_NEW_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getGneder());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getMobile());
            preparedStatement.setInt(6, customer.getLogin().getLoginId());
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
    public Customer getCustomerByCustomerId(int customerId) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(SELECT_ONE_CUSTOMER);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                int loginId = resultSet.getInt("login_id");
                String customerStatus = resultSet.getString("customer_status");

                Login login = new Login();
                login.setLoginId(loginId);

                Customer customer = new Customer(customerId, firstName, lastName, gender, email, mobile, login, customerStatus);

                return customer;

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

}
