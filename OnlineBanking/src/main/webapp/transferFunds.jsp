<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.daoImpl.UserTransactionDAOImpl" %>
<%@ page import="javax.servlet.http.HttpSession" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Funds</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }
        form {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #aaa;
        }
        input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Transfer Funds</h1>
    
    <form action="TransactionServlet" method="POST">
        <label for="receiverUsername">Receiver's Username:</label>
        <input type="text" id="receiverUsername" name="receiverUsername" required>
        <br>
        <label for="amount">Amount:</label>
        <input type="number" step="0.01" id="amount" name="amount" required>
        <br>
        <button type="submit">Transfer</button>
    </form>
    <a href="menu.jsp">Back to Menu</a>
</body>
</html>
