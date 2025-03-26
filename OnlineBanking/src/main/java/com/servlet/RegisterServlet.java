package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserTransactionDAO;
import com.daoImpl.UserTransactionDAOImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserTransactionDAO user = new UserTransactionDAOImpl();
		int register = user.registerUser(username, password);
		
		if(register > 0) {
			response.sendRedirect("success.html");
		}
		else {
			response.sendRedirect("failure.html");
		}
	}

}
