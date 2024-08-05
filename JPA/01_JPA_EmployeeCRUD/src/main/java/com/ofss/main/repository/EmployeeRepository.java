package com.ofss.main.repository;

import com.ofss.main.domain.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EmployeeRepository {
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	public void addNewEmployee(Employee employee) {
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(employee);
		transaction.commit();
		entityManager.clear();
	}
}
