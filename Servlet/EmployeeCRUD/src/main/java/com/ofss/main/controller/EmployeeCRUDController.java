package com.ofss.main.controller;

import java.io.IOException;
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
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in doOptions");
		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("employeeId") != null) {
			int employeeId = Integer.valueOf(request.getParameter("employeeId"));
			boolean result = employeeService.deleteEmployeeByEmployeeId(employeeId);
			System.out.println(result);
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Put method called");

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

		System.out.println(newEmployee);
		// Add new employee to list
		employeeService.updateEmployee(newEmployee);

		// Send response
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("text/xml");

		if (request.getParameter("employeeId") != null) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
			if (employee != null) {
				try {
					// Create a JAXBContext and Marshaller
					JAXBContext context = JAXBContext.newInstance(Employee.class);
					Marshaller marshaller = context.createMarshaller();

					// Marshal to StringWriter
					marshaller.marshal(employee, response.getWriter());

				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		} else {

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
