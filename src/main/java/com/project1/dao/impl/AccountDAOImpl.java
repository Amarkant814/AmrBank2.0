package com.project1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.dao.impl.AccountDAOImpl;
import com.project1.dbutil.PostgresConnection;
import com.project1.dao.AccountDAO;
import com.project1.exception.BusinessException;
import com.project1.model.Account;

public class AccountDAOImpl implements AccountDAO {

	private static Logger log = Logger.getLogger(AccountDAOImpl.class);

	@Override
	public Account getAccountById(int accountId) throws BusinessException {
		Account account = null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.account where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				account = new Account();
				account.setAccountId(resultSet.getInt("accountid"));
				account.setAccountBal(resultSet.getDouble("accountbal"));
				account.setValid(resultSet.getBoolean("valid"));
				account.setUserId(resultSet.getInt("userid"));
			} else {
				throw new BusinessException("No account exists as of now with the account No: " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return account;
	}

	@Override
	public List<Account> getAccountByBal(double accountBal) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.account where accountbal=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, accountBal);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("accountid"));
				account.setAccountBal(resultSet.getDouble("accountbal"));
				account.setValid(resultSet.getBoolean("valid"));
				account.setUserId(resultSet.getInt("userid"));
				accountList.add(account);
			}
			if (accountList.size() == 0) {
				throw new BusinessException("No account exists as of now with the account Bal: " + accountBal);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return accountList;
	}

	@Override
	public List<Account> getAccountByValid(boolean valid) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.account where valid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, valid);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("accountid"));
				account.setAccountBal(resultSet.getDouble("accountbal"));
				account.setValid(resultSet.getBoolean("valid"));
				account.setUserId(resultSet.getInt("userid"));
				accountList.add(account);
			}
			if (accountList.size() == 0) {
				throw new BusinessException("No account exists as of now with the given validity: " + valid);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return accountList;
	}

	@Override
	public List<Account> getAccountByUserId(int userId) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.account where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("accountid"));
				account.setAccountBal(resultSet.getDouble("accountbal"));
				account.setValid(resultSet.getBoolean("valid"));
				account.setUserId(resultSet.getInt("userid"));
				accountList.add(account);
			}
			if (accountList.size() == 0) {
				throw new BusinessException("No account exists as of now with the user id: " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return accountList;
	}

	@Override
	public Account createAccount(Account account) throws BusinessException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank.account(accountbal, valid, userid) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDouble(1, account.getAccountBal());
			preparedStatement.setBoolean(2, account.isValid());
			preparedStatement.setInt(3, account.getUserId());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					account.setAccountId(resultSet.getInt(1));
				}
			} else {
				throw new BusinessException("Account Registration Failed..Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			// throw new BusinessException("Internal error occured... Kindly conatct
			// SYSADMIN........");
		}

		return account;
	}

	@Override
	public Account updateAccount(int accountId, double accountBal) throws BusinessException {
		Account account = new Account();
		account.setAccountBal(accountBal);
		account.setAccountId(accountId);
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "update bank.account set accountbal = ? where accountid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, accountBal);
			preparedStatement.setInt(2, accountId);

			preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			// throw new BusinessException("Internal error occured... Kindly conatct
			// SYSADMIN........");
		}

		return account;
	}

	@Override
	public Account updateAccount(int accountId, boolean valid) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.account ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("accountid"));
				account.setAccountBal(resultSet.getDouble("accountbal"));
				account.setValid(resultSet.getBoolean("valid"));
				account.setUserId(resultSet.getInt("userid"));
				accountList.add(account);
			}
			if (accountList.size() == 0) {
				throw new BusinessException("No account in the database");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// log.error(e);//logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return accountList;
	}

	@Override
	public void deleteAccount(int accountId) throws BusinessException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "delete from bank.account where accountid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);

			preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			// log.error(e);
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
	}

}
