package com.cg.service;

import java.sql.SQLException;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public interface AccountOperation {
	public boolean addAccount(Account ob) throws SQLException;
	public double withdraw(Account ob, double amount) throws InsufficientFundException;
	public double deposite(Account ob, double amount);
	public boolean findAccount(Long mobileno) throws SQLException;
	
	
	boolean deleteAccount(long ob) throws SQLException;
	boolean getAllAccounts() throws SQLException;
}
