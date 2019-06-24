package com.cg.service;
import com.cg.dao.*;

import java.sql.SQLException;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;
import java.sql.*;
public class AccountService implements Gst,Transaction
{
AccountDAO dao=new AccountDAOImpl();
	@Override
	public double withdraw(Account ob, double amount) throws InsufficientFundException {
		
		double new_balance=ob.getBalance()-amount;
		if(new_balance<1000)
		{
			new_balance=ob.getBalance();
			
			//throw new RuntimeException("Insufficient Fund,Cannot process withdrawal");
			//System.out.println("Insufficient Balance");
		throw new InsufficientFundException("Insufficient Fund,Cannot process withdrawal",new_balance);
		
		}
		ob.setBalance(new_balance);
		System.out.println("Your new balance is:"+new_balance);
	return new_balance;
	}

	@Override
	public double deposite(Account ob, double amount) {
		double new_balance=ob.getBalance()+amount;
		ob.setBalance(new_balance);
		System.out.println("Your new balance is:"+new_balance);
		return new_balance;
	}



	@Override
	public double calculateTax(double PCT, double amount) {
		// TODO Auto-generated method stub
		return amount*Gst.PCT_5;
	}

	@Override
	public boolean addAccount(Account ob) throws SQLException {
		
		return dao.addAccount(ob);
	}


	@Override
	public boolean findAccount(Long mobileno) throws SQLException {
	
		return dao.findAccount(mobileno);
	}

	@Override
	public boolean getAllAccounts() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getAllAccounts();
	}

	@Override
	public boolean deleteAccount(long ob) throws SQLException {
		// TODO Auto-generated method stub
		
		return dao.deleteAccount(ob);
	}

	@Override
	public boolean transferMoney(long from, long to, double amount) throws SQLException {
		// TODO Auto-generated method stub
		return dao.transferMoney(from,to,amount);
		
	}

	

	
	
	

}
