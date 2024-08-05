package com.ofss.main;

import java.util.HashSet;

import com.ofss.main.domain.Address;
import com.ofss.main.domain.Employee;
import com.ofss.main.repository.EmployeeRepositoryImpl;

public class EmployeeCRUDMainV2 {
	public static void main(String[] args) {

		Employee employee1 = new Employee(0, "JPA First", "JPA Last", 1000, new HashSet<Address>());
		Employee employee2 = new Employee(0, "JPA Second", "JPA Last Second", 2000, new HashSet<Address>());

		Address address1 = new Address(0, "Nisarg CHS", "J.W. Road", 400012, new HashSet<Employee>());
		Address address2 = new Address(0, "Woodland Park", "M.G. Road", 10001, new HashSet<Employee>());

		employee1.getAddressSet().add(address1);
		employee1.getAddressSet().add(address2);

		EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl();
		employeeRepositoryImpl.addNewEmployee(employee1);
		employeeRepositoryImpl.addNewEmployee(employee2);

	}
}
