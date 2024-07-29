package com.ofss.main;

import java.io.File;

import com.ofss.main.domain.Employee;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XMLToObject {

	public static void main(String[] args) {
		try {
			File file = new File("employee.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Employee employee = (Employee) jaxbUnmarshaller.unmarshal(file);
			System.out.println(employee);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
