package com.ofss.main.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.ofss.main.domain.Employee;
import com.ofss.main.domain.EmployeeList;
import com.ofss.main.service.EmployeeService;
import com.ofss.main.service.EmployeeServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * Servlet implementation class EmployeeCRUDController
 */
public class EmployeeCRUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("text/xml");
		
		try {
			// Create a JAXBContext and Marshaller
			JAXBContext context = JAXBContext.newInstance(EmployeeList.class);
			Marshaller marshaller = context.createMarshaller();

			// Format XML output
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Marshal to StringWriter
			EmployeeList employeeList = new EmployeeList();
			ArrayList<Employee> employeesFromDb = (ArrayList<Employee>) employeeService.getAllEmployees();
			employeeList.setEmployeeList(employeesFromDb);
			marshaller.marshal(employeeList, response.getWriter());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Post method called");
		System.out.println("Add new employee start");

		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		// Read XML data from request body
		Employee newEmployee;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			newEmployee = (Employee) unmarshaller.unmarshal(request.getInputStream());
		} catch (JAXBException e) {
			throw new ServletException("Error reading XML data", e);
		}

		// Add new employee to list
		employeeService.addNewEmployee(newEmployee);
		System.out.println(newEmployee);
		// Send response
		response.setStatus(HttpServletResponse.SC_OK);
		System.out.println("Add new employee end");

	}

}
