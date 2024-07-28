package com.borntocode.main.service;

import java.util.ArrayList;

import com.borntocode.main.domain.Employee;

public interface EmployeeService {
	ArrayList<Employee> getAllEmployees();

	Employee getEmployeeByEmployeeId(int employeeId);
	
	boolean addNewEmployee(Employee employee);
	
}
