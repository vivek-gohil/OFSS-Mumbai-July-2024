package com.ofss.main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ofss.main.domain.Employee;

public class EmployeeRepostioryImpl implements EmployeeRepository {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/learningdb";
    private static final String userName = "root";
    private static final String password = "root";

    // connect to database
    private Connection connection = null;
    // store and execute query
    private PreparedStatement preparedStatement = null;
    // store result retrived from database
    private ResultSet resultSet = null;

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee_details";
    private static final String SELECT_ONE_EMPLOYEE = "SELECT * FROM employee_details WHERE employee_id = ?";

    @Override
    public List<Employee> getAllEmployees() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
            resultSet = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstname = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double salary = resultSet.getDouble("salary");

                Employee employee = new Employee(employeeId, firstname, lastName, salary);
                employeeList.add(employee);
            }

            return employeeList;
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
    public Employee getEmployeeByEmployeeId(int employeeId) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement(SELECT_ONE_EMPLOYEE);
            preparedStatement.setInt(1, employeeId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int eId = resultSet.getInt("employee_id");
                String firstname = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double salary = resultSet.getDouble("salary");

                Employee employee = new Employee(eId, firstname, lastName, salary);
                return employee;
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
    public boolean addNewEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewEmployee'");
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public boolean deleteEmployeeByEmployeeId(int employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployeeByEmployeeId'");
    }

}
