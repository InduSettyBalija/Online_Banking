package com.model; // Ensure the correct package name

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private String senderName;
    private String receiverName;
    private double amount;
    private Timestamp transactionDate;
    
    public Transaction() {
    	
    }

    // Constructor
    public Transaction(int transactionId, String senderName, String receiverName, double amount, Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getter methods
    public int getTransactionId() {
        return transactionId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }
}
