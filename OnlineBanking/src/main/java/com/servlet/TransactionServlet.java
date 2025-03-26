package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daoImpl.UserTransactionDAOImpl;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int senderId = (int)session.getAttribute("userId");
		System.out.println(senderId);
       
        
        String receiverUsername = request.getParameter("receiverUsername");
        double amount = Double.parseDouble(request.getParameter("amount"));
        
        UserTransactionDAOImpl transactionDAO = new UserTransactionDAOImpl();
        int success = transactionDAO.transferFunds(senderId, receiverUsername, amount);
        
        if (success > 0) {
            response.sendRedirect("transactionSuccessfull.html");
        }else if(success == 0) {
        	response.sendRedirect("transactionFailed.html");       	
        }
        
        else {
            response.sendRedirect("Insufficient.html");
        }
	}

}
