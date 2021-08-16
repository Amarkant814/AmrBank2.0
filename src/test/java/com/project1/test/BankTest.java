package com.project1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.project1.model.Account;
import com.project1.model.Transaction;
import com.project1.model.User;

public class BankTest {
	private static User user;
	private static Account account;
	private static Transaction transaction;
	@BeforeAll
	public static void setBankTest() {
		user = new User(1, "User@123", "Password@123");
		account = new Account(3000, 5590.54, false, 1);
		transaction = new Transaction(3874, "Credit", 500, 3000);
	}
	
	@Test
	public void testGetUserId() {
		int id = user.getUserId();
		assertEquals(1, id);
	}
	
	@Test
	public void testGetAccountId() {
		int id = account.getAccountId();
		assertEquals(3000, id);
	}
	
	@Test
	public void testGetTransactionId() {
		int id = transaction.getTransactionId();
		assertEquals(3874, id);
	}
	
	@Test
	public void testGetUserName() {
		User user = new User();
		user.setUserName("newuser");
		user.setPassword("password");
		String username = user.getUserName();
		assertEquals("newuser", username);
	}
	
	@Test
	public void testGetPassword() {
		User user = new User();
		user.setUserName("amar");
		user.setPassword("12345");
		String username = user.getPassword();
		assertEquals("12345", username);
	}
	
	@Test
	public void testPassword() {
		User user = new User();
		user.setPassword("12345");
		String username = user.getPassword();
		assertEquals("12345", username);
	}

//	private void asserEquals(String string, String username) {
//		// TODO Auto-generated method stub
//		
//	}

}
