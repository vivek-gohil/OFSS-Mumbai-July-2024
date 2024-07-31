package com.ofss.main.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ofss.main.service.LoginService;
import com.ofss.main.service.impl.LoginServiceImpl;

public class LoginController extends HttpServlet {

	private LoginService loginService = new LoginServiceImpl();

	public LoginController() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
