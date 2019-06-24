package com.cg.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.cg.bean.Account;
import java.sql.*;
public interface AccountDAO {
	public boolean addAccount(Account ob) throws SQLException;
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(long ob) throws SQLException;
	public boolean findAccount(Long mobileno) throws SQLException;
	public boolean getAllAccounts() throws SQLException;
	public boolean transferMoney(long from,long to,double bal) throws SQLException ;

}
