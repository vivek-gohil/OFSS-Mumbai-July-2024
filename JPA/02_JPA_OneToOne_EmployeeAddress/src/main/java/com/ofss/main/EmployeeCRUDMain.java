package com.ofss.main;

import com.ofss.main.domain.Address;
import com.ofss.main.domain.Employee;
import com.ofss.main.repository.EmployeeRepository;
import com.ofss.main.repository.EmployeeRepositoryImpl;

public class EmployeeCRUDMain {
	public static void main(String[] args) {
		System.out.println("main start");
		Address address = new Address(0, "Nisarg CHS", "J.W. Road", 400012);
		Employee employee = new Employee(0, "JPA First", "JPA Last", 1000, address);
		EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl();
		employeeRepositoryImpl.addNewEmployee(employee);
		System.out.println("main end");
	}
}
