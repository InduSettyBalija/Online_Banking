<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .btn {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Welcome to Online Banking</h1>
    <div class="card">
        <a href="checkBalance.jsp" class="btn">Check Balance</a>
        <a href="transferFunds.jsp" class="btn">Transfer Funds</a>
        <a href="transactionHistory.jsp" class="btn">View Transaction History</a>
        <a href="creditAmount.jsp" class="btn">Credit Amount</a>
        <a href="debitAmount.jsp" class="btn">Debit Amount</a>
    </div>
</body>
</html>
