package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserTransactionDAO;
import com.daoImpl.UserTransactionDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserTransactionDAO user = new UserTransactionDAOImpl();
		int userId = user.loginUser(username, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		
		if(userId > 0) {
		
			response.sendRedirect("menu.jsp");
		}
		else {
			response.sendRedirect("login.html");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Handle GET requests the same as POST
    }

}
