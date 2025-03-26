package com.dao;

public interface UserTransactionDAO {
	int registerUser(String username, String password);
	int loginUser(String username, String password);
    

}
