package com.ofss.main.domain;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
public class EmployeeList {
	private ArrayList<Employee> employeeList = new ArrayList<Employee>();

	@XmlElement(name = "employee")
	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
