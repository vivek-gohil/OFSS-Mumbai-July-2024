package com.ofss.main.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_details")
public class Address {

	@Id
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "house_number")
	private String houseNumber;

	@Column(name = "street")
	private String street;

	@Column(name = "pin")
	private int pin;

	@ManyToMany(mappedBy = "addressSet")
	private Set<Employee> employees;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int addressId, String houseNumber, String street, int pin, Set<Employee> employees) {
		super();
		this.addressId = addressId;
		this.houseNumber = houseNumber;
		this.street = street;
		this.pin = pin;
		this.employees = employees;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(addressId, employees, houseNumber, pin, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressId == other.addressId && Objects.equals(employees, other.employees)
				&& Objects.equals(houseNumber, other.houseNumber) && pin == other.pin
				&& Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", houseNumber=" + houseNumber + ", street=" + street + ", pin="
				+ pin + ", employees=" + employees + "]";
	}

}
