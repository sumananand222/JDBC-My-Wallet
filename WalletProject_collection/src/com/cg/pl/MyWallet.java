package com.cg.pl;
import com.cg.bean.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.cg.bean.*;
import com.cg.exception.InsufficientFundException;
import com.cg.service.AccountService;
import com.cg.service.Gst;
import com.cg.service.Validator;
public class MyWallet {

	public static void main(String[] args) throws IOException, SQLException {
		
		AccountService service=new AccountService();
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice="";
		while(true)
		{
		System.out.println("Menu");
		System.out.println("======");
		System.out.println("1->Create new Account");
		System.out.println("2->Print All Accounts");
		System.out.println("3->Deposit");
		System.out.println("4->Withdraw");
		System.out.println("5->Delete");
		System.out.println("6->TransferMoney");
		System.out.println("7->Find Account");
		System.out.println("8->exit");
		System.out.println("Enter your choice");
		choice=br.readLine();

		switch(choice)
		{
		case "1":
			int id=0;
			long mb=0L;
			String ah="";
			double bal=0.0;
			
			//Accepting and validating input for account number
			
			System.out.println("Enter account number:");
			while(true)
			{
				String s_id=br.readLine();
			    boolean ch1=Validator.validatedata(s_id,Validator.aidpattern);
			    if(ch1==true)
			    {
			    	try
			    	{
			    		id=Integer.parseInt(s_id);
			    		break;
			    	}
			    	catch(NumberFormatException e)
			    	{
			    		System.out.println("Account number must be numeric Reenter");
			    	}
			    }
			    else
			    {
			    	
			    	System.out.println("Re Enter Account Number in 3 digits");
			    }
			}
			//end of account number while

			//Accepting and validating input for mobile number
			System.out.println("Enter mobile number:");
			while(true)
			{
			String s_id=br.readLine();
			boolean ch1=Validator.validatedata(s_id,Validator.mobilepattern);
			if(ch1==true)
			{
				try
				{
					mb=Long.parseLong(s_id);
					break;
				}
				catch(NumberFormatException e)
				{
					System.out.println("Account number must be numeric Reenter");
				}
			}
			else
			{
				
				System.out.println("Re Enter Mobile Number (10 digits)");
			}
			}
			//end of account number while

			//accepting and validating account holder
			System.out.println("Enter Holder Name:");
			while(true)
			{
			String s_id=br.readLine();
			boolean ch1=Validator.validatedata(s_id,Validator.namepattern);
			if(ch1==true)
			{ah=s_id;
				break;
			}
			else
			{
				
				System.out.println("Re Enter name");
			}
			}
			//Accepting and validating input for Account balance
			System.out.println("Enter balance:");
			while(true)
			{
			String s_bal=br.readLine();
			boolean ch1=Validator.validatedata(s_bal,Validator.balancepattern);
			System.out.println(ch1);
			if(ch1==true)
			{
				try
				{
					 bal=Double.parseDouble(s_bal);
					break;
				}
				catch(NumberFormatException e)
				{
					System.out.println("Balance must be greater than 1000");
				}
			}
			else
			{
				
				System.out.println("Balance must be greater than 1000,Re Enter balance");
			}
			}
			//end of account numer while

			
			Account ob=new Account(id,mb,ah,bal);

                service.addAccount(ob);
			break;
			case "2":
				service.getAllAccounts();
				break;
		/*		
			case"3":
				Map<Long,Account>accmap1=service.getAllAccounts();
				System.out.println("Enter the Mobile number:");
				String s1=br.readLine();
				long mob=Long.parseLong(s1);
				System.out.println("Enter the amount");
				String s=br.readLine();
				double amount1=Double.parseDouble(s);
				service.deposite(accmap1.get(mob), amount1);
				break;
			case "4":
				accmap=service.getAllAccounts();
				System.out.println("Enter the Mobile number:");
				s1=br.readLine();
				mob=Long.parseLong(s1);
				System.out.println("Enter the amount");
			    s=br.readLine();
			    double amount2=Double.parseDouble(s);
			    Account ao = accmap.get(mob);
			
			    try {
				service.withdraw(ao,amount2);
			} catch (InsufficientFundException e) {
				// TODO Auto-generated catch block
				System.out.println("Insufficient fund");
			}
				break;*/
			case "5":
				
				System.out.println("Enter the Mobile number you wanna delete the account of:");
				String s1=br.readLine();
				long mob=Long.parseLong(s1);
				Boolean b=service.deleteAccount(mob);
				
				break;
		case "6":
				
				System.out.println("Enter your Mobile number:");
				s1=br.readLine();
				Long mob1=Long.parseLong(s1);
		
				System.out.println("Enter the Mobile number in which money to be transfered:");
				String s2=br.readLine();
				Long mob2=Long.parseLong(s2);
				System.out.println("Enter the amount to be Transfered:");
				String s=br.readLine();
			    double amount=Double.parseDouble(s);
				service.transferMoney(mob1,mob2,amount);
				break;
			case"7":
				
				System.out.println("Enter the Mobile number you wanna display:");
				s1=br.readLine();
				mob=Long.parseLong(s1);
				service.findAccount(mob);
				
				break;
			
			case "8":
				System.out.println("Exiting Program");
				System.exit(0);
				break;
			
			
		}
		
			
				}

				
						
		}


	}

