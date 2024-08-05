package com.ofss.main.repository;

import java.util.List;

import com.ofss.main.domain.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private EntityManager entityManager = null;
	private EntityTransaction entityTransaction = null;

	@Override
	public boolean addNewEmployee(Employee employee) {

		// for connection to db
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();
		entityTransaction = entityManager.getTransaction();

		// insert
		entityTransaction.begin();
		entityManager.persist(employee); // to save new employee to db
		entityTransaction.commit();
		entityManager.close();

		return true;
	}

	@Override
	public List<Employee> getAllEmployees() {

		// connection
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();

		// Query interface to write queries
		Query query = entityManager.createQuery("SELECT e FROM Employee e");
		List<Employee> employees = query.getResultList(); // returns array list
		entityManager.close();
		return employees;

	}

	@Override
	public Employee getEmployeeByEmployeeId(int employeeId) {
		// connection
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();
		Employee employee = entityManager.find(Employee.class, employeeId);
		return employee;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();
		Employee employeeDb = entityManager.find(Employee.class, employee.getEmployeeId());
		if (employeeDb != null) {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			employeeDb.setFirstName(employee.getFirstName());
			employeeDb.setLastName(employee.getLastName());
			employeeDb.setSalary(employee.getSalary());
			entityTransaction.commit();
			entityManager.close();
			return true;
		}
		entityManager.close();
		return false;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		entityManager = Persistence.createEntityManagerFactory("01_JPA_EmployeeCRUD").createEntityManager();

		Employee employeeDb = entityManager.find(Employee.class, employeeId);
		if (employeeDb != null) {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(employeeDb);
			entityTransaction.commit();
			entityManager.close();
			return true;
		}

		return false;
	}

}