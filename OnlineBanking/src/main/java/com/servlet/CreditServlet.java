package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserTransactionDAO;
import com.daoImpl.UserTransactionDAOImpl;

/**
 * Servlet implementation class CreditServlet
 */
@WebServlet("/CreditServlet")
public class CreditServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		double amount = Double.parseDouble(request.getParameter("amount"));
		 
		
		UserTransactionDAOImpl user = new UserTransactionDAOImpl();
		int userId = (int) session.getAttribute("userId");
		
		int success = user.creditAmount(userId, amount);

        if (success > 0) {
            response.sendRedirect("transactionSuccessfull.html");
        } else {
            response.sendRedirect("transactionFailedDC.html");
        }
		
		
	}

}
