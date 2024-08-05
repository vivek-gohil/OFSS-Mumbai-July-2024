package com.ofss.main;

import java.util.ArrayList;
import java.util.List;

import com.ofss.main.domain.Address;
import com.ofss.main.domain.Employee;
import com.ofss.main.repository.EmployeeRepository;
import com.ofss.main.repository.EmployeeRepositoryImpl;

public class EmployeeCRUDMain {
	public static void main(String[] args) {
		System.out.println("main start");
		Employee employee = new Employee(0, "JPA First", "JPA Last", 1000, null);

		Address address1 = new Address(0, "Nisarg CHS", "J.W. Road", 400012, employee);
		Address address2 = new Address(0, "Woodland Park", "J.W. Road", 400012, employee);
		Address address3 = new Address(0, "Shanti Niketan", "J.W. Road", 400012, employee);
		Address address4 = new Address(0, "Ram Niwas", "J.W. Road", 400012, employee);

		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address1);
		addressList.add(address2);
		addressList.add(address3);
		addressList.add(address4);

		employee.setAddressList(addressList);

		EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl();
		employeeRepositoryImpl.addNewEmployee(employee);
		System.out.println("main end");
	}
}
