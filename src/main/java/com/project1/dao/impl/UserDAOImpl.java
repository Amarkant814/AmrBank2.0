package com.project1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.dbutil.PostgresConnection;
import com.project1.dao.UserDAO;
import com.project1.exception.BusinessException;
import com.project1.model.User;

public class UserDAOImpl implements UserDAO {

	private static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> getUsersByName(String name) throws BusinessException {
		List<User> userList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select userid, username from bank.user where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setUserName(resultSet.getString("username"));
				userList.add(user);
			}
			if (userList.size() == 0) {
				throw new BusinessException("No users exists as of now with the username : " + name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return userList;

	}

	@Override
	public User getUserByName(String name) throws BusinessException {
		User user = null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from bank.user where username =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt(1));
				user.setUserName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
			}
			else {
				throw new BusinessException("No such User exists with the email: "+name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured... ");
		}
		return user;
	}

	@Override
	public User getUserById(int userId) throws BusinessException {

		User user = null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select userid, username from bank.user where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setUserName(resultSet.getString("username"));
			} else {
				throw new BusinessException("No user exists as of now with the id: " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return user;
	}

	@Override
	public User createUser(User user) throws BusinessException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank.user( username, password) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					user.setUserId(resultSet.getInt(1));
				}
			} else {
				throw new BusinessException("User Registration Failure Please Retry!!!");
			}
		} catch (ClassNotFoundException e) {
			log.error(e);
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		catch(SQLException e) {
			log.info("Username already exists.. Please try with another username ");
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() throws BusinessException {
		List<User> userList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select userid, username from bank.user ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setUserName(resultSet.getString("username"));
				userList.add(user);
			}
			if (userList.size() == 0) {
				throw new BusinessException("No users exists in the database : ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);// logger
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return userList;
	}

	@Override
	public void deleteUser(int userId) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
