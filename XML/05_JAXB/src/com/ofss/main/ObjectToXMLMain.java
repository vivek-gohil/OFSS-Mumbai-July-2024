package com.ofss.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.ofss.main.domain.Employee;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class ObjectToXMLMain {
	public static void main(String[] args) {
		System.out.println("main start");

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = context.createMarshaller();
			Employee employee = new Employee(101, "Seema", 70000);
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(employee, new FileOutputStream("employee.xml"));
			System.out.println("Please check your file");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("main end");
	}
}
