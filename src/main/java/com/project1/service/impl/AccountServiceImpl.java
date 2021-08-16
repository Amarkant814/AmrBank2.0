package com.project1.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.project1.dao.AccountDAO;
import com.project1.dao.impl.AccountDAOImpl;
import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.service.AccountService;

public class AccountServiceImpl implements AccountService{

	@Override
	public Account getAccountById(int accountId) throws BusinessException {
		Account account = null;
		AccountDAO accountDAO = new AccountDAOImpl();
		account = accountDAO.getAccountById(accountId);
		return account;
	}

	@Override
	public List<Account> getAccountByBal(double accountBal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountByValid(boolean valid) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountByUserId(int userId) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		AccountDAO accountdao = new AccountDAOImpl();
		accountList = accountdao.getAccountByUserId(userId);
		return accountList;
	}

	@Override
	public Account createAccount(Account account) throws BusinessException {
		AccountDAO accountDAO = new AccountDAOImpl();
		if(account!=null) {
			account = accountDAO.createAccount(account);
		}
		return account;
	}

	@Override
	public Account updateAccount(int accountId, double accountBal) throws BusinessException {
		Account account = null;
		AccountDAO accountDAO = new AccountDAOImpl();
		account = accountDAO.updateAccount(accountId, accountBal);
		return account;
	}

	@Override
	public Account updateAccount(int accountId, boolean valid) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() throws BusinessException {
		List<Account> accountList= new ArrayList<>();
		AccountDAO accountDAO = new AccountDAOImpl();
		accountList = accountDAO.getAllAccounts();
		return accountList;
	}

	@Override
	public void deleteAccount(int accountId) throws BusinessException {
		AccountDAO accountDAO = new AccountDAOImpl();
		accountDAO.deleteAccount(accountId);
		
	}

}
