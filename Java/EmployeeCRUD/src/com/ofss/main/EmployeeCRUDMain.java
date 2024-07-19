package com.ofss.main;

import java.util.List;

import com.ofss.main.domain.Employee;
import com.ofss.main.service.EmployeeService;
import com.ofss.main.service.EmployeeServiceImpl;

public class EmployeeCRUDMain {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();

        List<Employee> employeeList = employeeService.getAllEmployees();

        System.out.println("All Employees From Database");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
