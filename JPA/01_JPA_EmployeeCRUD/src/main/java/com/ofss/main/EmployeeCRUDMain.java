package com.ofss.main;

import com.ofss.main.domain.Employee;
import com.ofss.main.repository.EmployeeRepository;

public class EmployeeCRUDMain {
	public static void main(String[] args) {
		System.out.println("main start");
		Employee employee = new Employee(0, "JPA First", "JPA Last", 1000);
		EmployeeRepository employeeRepository = new EmployeeRepository();
		employeeRepository.addNewEmployee(employee);
		System.out.println("main end");
	}
}
