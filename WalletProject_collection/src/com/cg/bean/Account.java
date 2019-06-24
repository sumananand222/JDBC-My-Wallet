package com.cg.bean;

import java.util.Comparator;

public  class Account implements Comparable<Account> {
	private int aid;
	private long mobile;
	private String accountholder;
	private double balance;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(int aid, long mobile, String accountholder, double balance) {
		super();
		this.aid = aid;
		this.mobile = mobile;
		this.accountholder = accountholder;
		this.balance = balance;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAccountholder() {
		return accountholder;
	}
	public void setAccountholder(String accountholder) {
		this.accountholder = accountholder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", mobile=" + mobile + ", accountholder=" + accountholder + ", balance="
				+ balance + "]";
	}
	
	public int compareTo(Account arg) {
		//default sorting based on account num
		System.out.println(this.getAid()+" compare to "+arg.getAid());
		int diff=this.getAid()-arg.getAid();
		if(diff>0)
			return 1;
		else if(diff<0)
			return -1;
		else
			return 0;
	}

}
