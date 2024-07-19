package com.ofss.main.service;

import java.util.List;

import com.ofss.main.domain.Employee;

public interface EmployeeService {
List<Employee> getAllEmployees();

    Employee getEmployeeByEmployeeId(int employeeId);

    boolean addNewEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployeeByEmployeeId(int employeeId);
}
