package com.project1.model;


public class Account {
	
	private int accountId;
	private double accountBal;
	private boolean valid = true;
	private int userId;
	
	
	public Account() {
		super();
	}


	public Account(int accountId, double accountBal, boolean valid, int userId) {
		super();
		this.accountId = accountId;
		this.accountBal = accountBal;
		this.valid = valid;
		this.userId = userId;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public double getAccountBal() {
		return accountBal;
	}


	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountBal=" + accountBal + ", valid=" + valid + ", userId="
				+ userId + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountId;
		result = prime * result + userId;
		result = prime * result + (valid ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(accountBal) != Double.doubleToLongBits(other.accountBal))
			return false;
		if (accountId != other.accountId)
			return false;
		if (userId != other.userId)
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}
	
	
	


	
	

}
