package com.borntocode.main.controller;

import java.io.IOException;
import java.io.StringWriter;

import com.borntocode.main.domain.Employee;
import com.borntocode.main.domain.Employees;
import com.borntocode.main.service.EmployeeService;
import com.borntocode.main.service.EmployeeServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService = new EmployeeServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.setContentType("text/xml");
		if (request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));

			Employee employee = employeeService.getEmployeeByEmployeeId(id);
			if (employee != null) {
				try {
					// Create a JAXBContext and Marshaller
					JAXBContext context = JAXBContext.newInstance(Employee.class);
					Marshaller marshaller = context.createMarshaller();

					// Format XML output
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

					marshaller.marshal(employee, response.getWriter());

				} catch (JAXBException e) {
					e.printStackTrace();
				}
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}

		} else {
			try {
				Employees employees = new Employees();

				employees.setEmployees(employeeService.getAllEmployees());

				// Create a JAXBContext and Marshaller
				JAXBContext context = JAXBContext.newInstance(Employees.class);
				Marshaller marshaller = context.createMarshaller();

				// Format XML output
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				// Marshal to StringWriter
				StringWriter writer = new StringWriter();
				marshaller.marshal(employees, response.getWriter());

				// Output the XML
				System.out.println(writer.toString());

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		response.setStatus(HttpServletResponse.SC_CREATED);
		System.out.println("Add new employee end");
	}

}