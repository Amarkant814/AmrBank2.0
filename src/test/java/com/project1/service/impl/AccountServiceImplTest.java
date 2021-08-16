package com.project1.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.service.AccountService;

class AccountServiceImplTest extends AccountServiceImpl {

	private static Account account=null;
	
	@Test
	void testGetAccountById() {
		AccountService accountService = new AccountServiceImpl();
		try {
			account = accountService.getAccountById(30005);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double bal = account.getAccountBal();
		assertEquals(5899.0,bal);
	}

	@Test
	void testGetAccountByUserId() {
		AccountService accountService = new AccountServiceImpl();
		try {
			account = accountService.getAccountById(30016);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double bal = account.getAccountBal();
		assertEquals(10100.0,bal);
	}

	@Test
	void testGetAllAccounts() {
		AccountService accountService = new AccountServiceImpl();
		try {
			account = accountService.getAccountById(30022);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double bal = account.getAccountBal();
		assertEquals(4376.0,bal);
	}

}
