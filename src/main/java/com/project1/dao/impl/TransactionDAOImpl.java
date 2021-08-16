package com.project1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.dao.TransactionDAO;
import com.project1.dbutil.PostgresConnection;
import com.project1.exception.BusinessException;
import com.project1.model.Transaction;

public class TransactionDAOImpl implements TransactionDAO{

	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);
	
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
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getInt("transactionid"));
				transaction.setType(resultSet.getString("type"));
				transaction.setTransactionAmount(resultSet.getDouble("transactionamount"));
				transaction.setAccountId(resultSet.getInt("accountid"));
				transactionList.add(transaction);
			}
			if(transactionList.size()==0) {
				throw new BusinessException("No transation exists as of now with the account id: "+accountId);
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			//throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return transactionList;
	}

	@Override
	public Transaction createTransaction(Transaction transaction) throws BusinessException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="insert into bank.transaction(type, transactionamount, accountid) values(?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, transaction.getType());
			preparedStatement.setDouble(2, transaction.getTransactionAmount());
			preparedStatement.setInt(3, transaction.getAccountId());
			
			int c=preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					transaction.setAccountId(resultSet.getInt(1));
				}
			}else {
				throw new BusinessException("Account Registration Failed..Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			//throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		return transaction;
	}
		

	@Override
	public Transaction updateTransaction(int transactionId, String type) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank.transaction ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getInt("transactionid"));
				transaction.setType(resultSet.getString("type"));
				transaction.setTransactionAmount(resultSet.getDouble("transactionamount"));
				transaction.setAccountId(resultSet.getInt("accountid"));
				transactionList.add(transaction);
			}
			if(transactionList.size()==0) {
				throw new BusinessException("No transation exists in the database");
			}
		}catch (ClassNotFoundException | SQLException e) {
			//log.error(e);//logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return transactionList;
	}

	@Override
	public void deleteTransaction(int accountId) throws BusinessException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="delete from bank.transaction where accountid =?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			preparedStatement.executeUpdate();
			
			
		}catch (ClassNotFoundException | SQLException e) {
			//log.error(e);//logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
	}

}
