package com.ofss.main.controller;

import java.io.IOException;

import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.service.LoginService;
import com.ofss.main.service.impl.LoginServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class LoginController extends HttpServlet {

	private LoginService loginService = new LoginServiceImpl();

	public LoginController() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost :: LoginController ");
		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		Customer customer;
		Login login;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			customer = (Customer) unmarshaller.unmarshal(request.getInputStream());
			System.out.println(customer);
			login = customer.getLogin();
			System.out.println(login);
		} catch (JAXBException e) {
			throw new ServletException("Error reading XML data", e);
		}

		int loginStatus = loginService.validateLogin(customer.getCustomerId(), login.getPassword());
		System.out.println(loginStatus);
		response.getWriter().write(loginStatus);

	}

}
