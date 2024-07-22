package com.ofss.main;

import java.util.List;
import java.util.Scanner;

import com.ofss.main.domain.Employee;
import com.ofss.main.service.EmployeeService;
import com.ofss.main.service.EmployeeServiceImpl;

public class EmployeeCRUDMain {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        int crudChoice;
        int employeeId;
        String continueChoice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Employee CRUD Menu");
            System.out.println("1. Add New Employee");
            System.out.println("2. Update Existing Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Print One Employee");
            System.out.println("5. Print All Employees");
            System.out.println("Enter your choice");
            crudChoice = scanner.nextInt();
            switch (crudChoice) {
                case 5:
                    System.out.println("All Employees From Database");
                    List<Employee> employeeList = employeeService.getAllEmployees();
                    for (Employee employee : employeeList) {
                        System.out.println(employee);
                    }
                    break;
                case 4:
                    System.out.println("Enter employeeId");
                    employeeId = scanner.nextInt();
                    Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
                    if (employee != null)
                        System.out.println(employee);
                    else
                        System.out.println("Invalid employeeeId");
                    break;
                case 1:
                    System.out.println("Enter first name");
                    String firstName = scanner.next();
                    System.out.println("Enter last name");
                    String lastName = scanner.next();
                    System.out.println("Enter salary");
                    double salary = scanner.nextDouble();

                    Employee insertEmployee = new Employee(0, firstName, lastName, salary);
                    boolean result = employeeService.addNewEmployee(insertEmployee);
                    if (result)
                        System.out.println("Employee added successfully");
                    else
                        System.out.println("Failed to add new employee");

                    break;
                case 2:
                    System.out.println("Enter employeeId");
                    employeeId = scanner.nextInt();
                    System.out.println("Enter New first name");
                    firstName = scanner.next();
                    System.out.println("Enter New last name");
                    lastName = scanner.next();
                    System.out.println("Enter New salary");
                    salary = scanner.nextDouble();

                    Employee updateEmployee = new Employee(employeeId, firstName, lastName, salary);
                    result = employeeService.updateEmployee(updateEmployee);
                    if (result)
                        System.out.println("Employee updated successfully");
                    else
                        System.out.println("Failed to update employee");

                    break;
                case 3:
                    System.out.println("Enter employeeId");
                    employeeId = scanner.nextInt();
                    result = employeeService.deleteEmployeeByEmployeeId(employeeId);
                    if (result)
                        System.out.println("Employee updated successfully");
                    else
                        System.out.println("Failed to update employee");

                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            System.out.println("Do you want to continue");
            continueChoice = scanner.next();
        } while (continueChoice.equalsIgnoreCase("Yes"));

    }
}
