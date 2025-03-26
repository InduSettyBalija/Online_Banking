package com.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.UserTransactionDAO;
import com.model.Transaction;


public class UserTransactionDAOImpl implements UserTransactionDAO {
	
	static Connection connection;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet;

	private static Scanner scanner = new Scanner(System.in);
	static String query;
	
	static {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system","root","root");
		} 
		catch (SQLException | ClassNotFoundException  e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int registerUser(String username, String password) {
		int rowsAffected=0;
		try {
            
            query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            rowsAffected = preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
		return rowsAffected;
		
	}

	@Override
	public int loginUser(String username, String password) {
		int userId = 0;
		try {
            

            query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            } 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
		return userId;
		
	}
	public int debitAmount(int userId,double amount) {
		int rowsUpdated = 0;
    	try {
    		query = "UPDATE users SET balance = balance - ? WHERE user_id = ?";
    		preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setDouble(1, amount);
    		preparedStatement.setInt(2, userId);

            rowsUpdated = preparedStatement.executeUpdate();
    		
    	}
    	catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    	
		return rowsUpdated;
		
	}
	
    public int creditAmount(int userId,double amount) {
    	int rowsUpdated = 0;
    	try {
    		query = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
    		preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setDouble(1, amount);
    		preparedStatement.setInt(2, userId);

            rowsUpdated = preparedStatement.executeUpdate();
    		
    	}
    	catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    	
		return rowsUpdated;
	}
	
	
	public double checkBalance(int userId) {
		double balance = 0.0;
        try {
            query = "SELECT balance FROM Users WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            } 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return balance;
    }
	
	public static int transferFunds(int senderId,String receiverUsername,double amount) {
		int result = 0;
        try {
            

            connection.setAutoCommit(false);

            String findRecipientQuery = "SELECT user_id FROM Users WHERE username = ?";
            PreparedStatement findRecipientStmt = connection.prepareStatement(findRecipientQuery);
            findRecipientStmt.setString(1, receiverUsername);

            ResultSet recipientResult = findRecipientStmt.executeQuery();
            if (!recipientResult.next()) {
                System.out.println("Recipient not found.");
                connection.rollback();
                return 0;
            }

            int recipientId = recipientResult.getInt("user_id");

            String balanceQuery = "SELECT balance FROM Users WHERE user_id = ?";
            PreparedStatement balanceStmt = connection.prepareStatement(balanceQuery);
            balanceStmt.setInt(1, senderId);

            ResultSet balanceResult = balanceStmt.executeQuery();
            if (balanceResult.next()) {
                double senderBalance = balanceResult.getDouble("balance");
                if (senderBalance < amount) {
                    System.out.println("Insufficient balance.");
                    connection.rollback();
                    return -1;
                }
            }

            String deductQuery = "UPDATE Users SET balance = balance - ? WHERE user_id = ?";
            PreparedStatement deductStmt = connection.prepareStatement(deductQuery);
            deductStmt.setDouble(1, amount);
            deductStmt.setInt(2, senderId);
            deductStmt.executeUpdate();

            String addQuery = "UPDATE Users SET balance = balance + ? WHERE user_id = ?";
            PreparedStatement addStmt = connection.prepareStatement(addQuery);
            addStmt.setDouble(1, amount);
            addStmt.setInt(2, recipientId);
            addStmt.executeUpdate();

            String transactionQuery = "INSERT INTO Transactions (sender_id, receiver_id, amount) VALUES (?, ?, ?)";
            PreparedStatement transactionStmt = connection.prepareStatement(transactionQuery);
            transactionStmt.setInt(1, senderId);
            transactionStmt.setInt(2, recipientId);
            transactionStmt.setDouble(3, amount);
            result = transactionStmt.executeUpdate();

            connection.commit();
            System.out.println("Funds transferred successfully!");
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Transaction failed. Rolling back...");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error: " + e.getMessage());
        } 
        return result;
    }
	
	public List<Transaction> viewTransactionHistory(int userId) {
	    List<Transaction> transactions = new ArrayList<>();
	    String query = """
	        SELECT t.transaction_id, s.username AS sender_name, 
	               r.username AS receiver_name, t.amount, t.transaction_date
	        FROM Transactions t
	        LEFT JOIN Users s ON t.sender_id = s.user_id
	        LEFT JOIN Users r ON t.receiver_id = r.user_id
	        WHERE t.sender_id = ? OR t.receiver_id = ?
	        ORDER BY t.transaction_date DESC
	    """;
	    
	    try  {
	    	preparedStatement = connection.prepareStatement(query);
	        
	        preparedStatement.setInt(1, userId);
	        preparedStatement.setInt(2, userId);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Transaction transaction = new Transaction(
	                    resultSet.getInt("transaction_id"),
	                    resultSet.getString("sender_name"),
	                    resultSet.getString("receiver_name"),
	                    resultSet.getDouble("amount"),
	                    resultSet.getTimestamp("transaction_date")
	               );
	                transactions.add(transaction);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return transactions;
	}

}

	


