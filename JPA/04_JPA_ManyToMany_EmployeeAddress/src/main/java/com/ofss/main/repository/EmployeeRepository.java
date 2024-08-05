package com.ofss.main.repository;

import java.util.List;

import com.ofss.main.domain.Employee;

public interface EmployeeRepository {

	boolean addNewEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeByEmployeeId(int employeeId);

	boolean updateEmployee(Employee employee);

	boolean deleteEmployee(int employeeId);

}
