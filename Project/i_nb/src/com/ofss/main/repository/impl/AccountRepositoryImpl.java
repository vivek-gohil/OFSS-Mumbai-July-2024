package com.ofss.main.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ofss.main.domain.Account;
import com.ofss.main.domain.Customer;
import com.ofss.main.repository.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/i_nb";
    private static final String userName = "root";
    //private static final String password = "Trupt!V!vek@143";
    private static final String password = "root";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String CREATE_NEW_ACCOUNT = "INSERT INTO account_details(customer_id,account_type,balance) VALUES(?,?,10000)";
    private static final String SELECT_ALL_ACCOUNTS_BY_CUSTOMER_ID_= "SELECT * FROM account_details WHERE customer_id= ?";

    @Override
    public int addNewAccount(Account account) {
       try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(CREATE_NEW_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,account.getCustomer().getCustomerId());
            preparedStatement.setString(2, account.getAccountType());
            int rowCount= preparedStatement.executeUpdate();

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
        return  0;
    }

    @Override
    public List<Account> getAllAccountsByCustomerId(int customerId) {
       try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS_BY_CUSTOMER_ID_);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            List<Account> accountList = new ArrayList<>();
            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                int custId = resultSet.getInt("customer_id");
                String accountType = resultSet.getString("account_type");
                double balance = resultSet.getDouble("balance");

                Customer customer = new Customer();
                customer.setCustomerId(custId);

                Account account = new Account(accountId, customer, accountType, balance);

                accountList.add(account);

            }
            return accountList;
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
