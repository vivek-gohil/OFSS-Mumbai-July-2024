package com.ofss.main.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebSevlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class HelloWorldController
 */
@WebServlet("/HelloWorldController")
public class HelloWorldController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection connection = null;

		try {
			// Step 1: Set up JNDI properties
			Properties properties = new Properties();
			properties.setProperty(
					Context.INITIAL_CONTEXT_FACTORY, 
					"weblogic.jndi.WLInitialContextFactory");
			
			properties.setProperty(
					Context.PROVIDER_URL, "http://localhost:7001"); 
																	

			// Step 2: Create an InitialContext
			Context initialContext = new InitialContext(properties);

			// Step 3: Lookup the DataSource
			DataSource dataSource = (DataSource) initialContext.lookup("employeecrud/jndi"); // Replace with your JNDI name

			// Step 4: Obtain a Connection from the DataSource
			connection = dataSource.getConnection();

			if (connection != null)
				response.getWriter().print("Connection Successfull");
			else
				response.getWriter().print("Connection Failed");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
