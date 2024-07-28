package com.borntocode.main.service;

import java.util.ArrayList;
import java.util.List;

import com.borntocode.main.domain.Employee;
import com.borntocode.main.domain.Employees;

public class EmployeeServiceImpl implements EmployeeService {

	// Create the wrapper and add employees
	Employees employees = new Employees();

	public EmployeeServiceImpl() {
		Employee emp1 = new Employee("John Doe", 1);
		Employee emp2 = new Employee("Jane Smith", 2);

		employees.getEmployees().add(emp1);
		employees.getEmployees().add(emp2);
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		return employees.getEmployees();
	}

	@Override
	public Employee getEmployeeByEmployeeId(int employeeId) {
		ArrayList<Employee> allEmployees = employees.getEmployees();
		for (Employee employee : allEmployees) {
			if (employee.getId() == employeeId) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public boolean addNewEmployee(Employee employee) {
		employees.getEmployees().add(employee);
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println(employees.getEmployees());
		System.out.println("+++++++++++++++++++++++++++++++");
		return true;
	}

}
