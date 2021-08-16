package com.project1.service;

import java.util.List;

import com.project1.exception.BusinessException;
import com.project1.model.Account;

public interface AccountService {
	public Account getAccountById(int accountId)throws BusinessException;
	public List<Account> getAccountByBal(double accountBal)throws BusinessException;
	public List<Account> getAccountByValid(boolean valid)throws BusinessException;
	public List<Account> getAccountByUserId(int userId)throws BusinessException;

	public Account createAccount(Account account) throws BusinessException;
	public Account updateAccount(int accountId,double accountBal) throws BusinessException;
	public Account updateAccount(int accountId,boolean valid) throws BusinessException;
	public List<Account> getAllAccounts() throws BusinessException;
	public void deleteAccount(int accountId) throws BusinessException;

}
