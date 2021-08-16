package com.project1.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.project1.dao.TransactionDAO;
import com.project1.dao.impl.TransactionDAOImpl;
import com.project1.exception.BusinessException;
import com.project1.model.Transaction;
import com.project1.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public Transaction getTransactionById(int transactionId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByType(String type) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByAccountId(int accountId) throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		TransactionDAO ts = new TransactionDAOImpl();
		transactionList = ts.getTransactionByAccountId(accountId);
		return transactionList;
	}

	@Override
	public Transaction createTransaction(Transaction transaction) throws BusinessException {
		Transaction transaction1 = new Transaction();
		TransactionDAO transactionDAO = new TransactionDAOImpl();
		transaction1 = transactionDAO.createTransaction(transaction);
		return transaction1;
	}

	@Override
	public Transaction updateTransaction(int transactionId, String type) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		TransactionDAO ts = new TransactionDAOImpl();
		transactionList = ts.getAllTransactions();
		return transactionList;
	}

	@Override
	public void deleteTransaction(int accountId) throws BusinessException {
		
		TransactionDAO ts = new TransactionDAOImpl();
		ts.deleteTransaction(accountId);
	}
	
	

}
