<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.daoImpl.UserTransactionDAOImpl, com.model.Transaction" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
   

    int userId = (int)session.getAttribute("userId");
    UserTransactionDAOImpl userDAO = new UserTransactionDAOImpl();
    List<Transaction> transactions = userDAO.viewTransactionHistory(userId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Transaction History</h1>
    <table>
        <tr>
            <th>Transaction ID</th>
            <th>Sender</th>
            <th>Receiver</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <% for (Transaction transaction : transactions) { %>
            <tr>
                <td><%= transaction.getTransactionId() %></td>
                <td><%= transaction.getSenderName() %></td>
                <td><%= transaction.getReceiverName() %></td>
                <td>$<%= transaction.getAmount() %></td>
                <td><%= transaction.getTransactionDate() %></td>
            </tr>
        <% } %>
    </table>
    <a href="menu.jsp" class="btn">Back to Menu</a>
</body>
</html>
