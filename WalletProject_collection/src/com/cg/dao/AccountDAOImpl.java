package com.cg.dao;
import com.cg.bean.*;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.cg.bean.Account;
import java.sql.*;
public class AccountDAOImpl implements AccountDAO {

	static Map<Long,Account> accmap=new HashMap<Long,Account>();
	
	
	
	
	public static Connection getConnect() throws SQLException
	{
			
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//java 8 automatically load the driver,so no need for above line
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String pass="hr";
			Connection con=DriverManager.getConnection(url,user,pass);

		
		return con;
		
	}
	@Override
	public boolean addAccount(Account ob) throws SQLException {
		Connection con=getConnect();
		System.out.println("Connected");
		con.setAutoCommit(false);
		
		 PreparedStatement updateSt=con.prepareStatement("insert into account values(?,?,?,?)");
		 updateSt.setInt(1,ob.getAid());
		 updateSt.setLong(2,ob.getMobile());
		 updateSt.setString(3,ob.getAccountholder());
		 updateSt.setDouble(4,ob.getBalance());
		 int num=updateSt.executeUpdate();
		 if(num!=0)
		 {
			 con.commit();
			 con.close();
	return true;
		 }
		 else
			 return false;
		 
	}

	@Override
	public boolean updateAccount(Account ob) {
		
		
		return true;
	}

	@Override
	public boolean deleteAccount(long mob) throws SQLException {
		Connection con=getConnect();
		System.out.println("Connected");
		System.out.println("coming:"+mob);
		con.setAutoCommit(false);
		
		 PreparedStatement updateSt=con.prepareStatement("delete from account where mobileno=?");
		 updateSt.setLong(1,mob);
		 int num=updateSt.executeUpdate();
		 if(num!=0)
		 {
			 System.out.println(num+" Account updated");
			 con.commit();
			 con.close();
	return true;
		 }
		 else
			 return false;
		
	}

	@Override
	public boolean findAccount(Long mobileno) throws SQLException {
		Connection con=getConnect();
		System.out.println("Connected");
		System.out.println("coming:"+mobileno);
		con.setAutoCommit(false);
		
		 PreparedStatement updateSt=con.prepareStatement("select * from account where mobileno=?");
		 updateSt.setLong(1,mobileno);
		 ResultSet rs=updateSt.executeQuery();
		 
		 if(rs.next()!=false)
		 {
			 if(rs.next())//we have to call next else code wont work
			 {
			 System.out.println("Account id:"+rs.getInt("aid"));
			 System.out.println("Mobile number:"+rs.getLong("mobileno"));
			 System.out.println("Account Holder name:"+rs.getString("accountholder"));
			 System.out.println("Account Balance:"+rs.getDouble("balance"));
			 con.commit();
			 con.close();
	
		 }
			 return true;
		 }
		 else
		 {
			 System.out.println("Account not found");
			 return false;
	}
		
		 }

	@Override
	public boolean  getAllAccounts() throws SQLException {
		
		Connection con=getConnect();
		System.out.println("Connected");
		
		con.setAutoCommit(false);
		
		 PreparedStatement selectSt=con.prepareStatement("select * from account");
		 
		 ResultSet rs=selectSt.executeQuery();
		 
		 if(rs.getFetchSize()>0)
		 {
			 //rs.previous();
			 while(rs.next())//we have to call next else code wont work
			 {
				 System.out.println("=========================");
			 System.out.println("Account id:"+rs.getInt("aid"));
			 System.out.println("Mobile number:"+rs.getLong("mobileno"));
			 System.out.println("Account Holder name:"+rs.getString("accountholder"));
			 System.out.println("Account Balance:"+rs.getDouble("balance"));
	
		 }
			 con.commit();
			 con.close();

			 return true;
		 }
		 else
		 {
			 
			 return false;
	}
		
		 }
	@Override
	public boolean transferMoney(long from, long to, double amount) throws SQLException {
		
		Connection con=getConnect();
		System.out.println("Connected");
		System.out.println("Mob1:"+from);
		System.out.println("Mob2:"+to);
		con.setAutoCommit(false);
		
		 PreparedStatement selectSt1=con.prepareStatement("select * from account where mobileno=?");
		PreparedStatement selectSt2=con.prepareStatement("select * from account where mobileno=?");
			selectSt1.setLong(1,from);
		 ResultSet rs1=selectSt1.executeQuery();
//		 con.commit();
		 selectSt2.setLong(1,to); 
		 
		 ResultSet rs2=selectSt2.executeQuery();
		 
		 int aid1=0;
		 long mobileno1=0L;
		 String name1="";
		 double bal1=0.0;
		 if(rs2!=null)
			{System.out.println("hello");
							
				while(rs2.next())
				{
					 
					System.out.println("You are transferring to:::");
					
					mobileno1=rs2.getLong("mobileno");
					System.out.println(mobileno1);
					name1=rs2.getString(3);
					System.out.println("Name:"+name1);
			
				}
			}
		 
		 
		 if(rs1!=null)
			{System.out.println("Hello");
				
				while(rs1.next())
				{
					String name2=rs1.getString(3);
					System.out.println("Your Name:"+name2);
					
									bal1=rs1.getDouble("balance");
									System.out.println("Your current balance:"+bal1);
	
				}
			}

		 PreparedStatement updateSt=con.prepareStatement("update account set balance=? where mobileno=?");
			double bal2=bal1-amount;
			System.out.println("New balance:"+bal2);
			updateSt.setDouble(1,bal2);
			updateSt.setLong(2,from);
			int i1=updateSt.executeUpdate();
			
	System.out.println("Your new account balance is "+(bal2));
	con.commit();
	return true;
	}
		
}

	

	

