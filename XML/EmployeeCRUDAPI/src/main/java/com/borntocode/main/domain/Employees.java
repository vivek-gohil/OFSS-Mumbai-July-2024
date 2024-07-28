package com.borntocode.main.domain;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
public class Employees {
	private ArrayList<Employee> employees;

	public Employees() {
		this.employees = new ArrayList<>();
	}

	@XmlElement(name = "employee")
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
}
