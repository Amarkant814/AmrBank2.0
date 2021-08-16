package com.project1.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.project1.dao.UserDAO;
import com.project1.dao.impl.UserDAOImpl;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO = new UserDAOImpl();
	
	
	@Override
	public List<User> getUsersByName(String name) throws BusinessException {
		List<User> userList = null;
		if(name!=null ) {
			userList = userDAO.getUsersByName(name);
		}else {
			throw new BusinessException("Invalid User Name : "+name);
		}
		return userList;
	}
	
	
	@Override
	public User getUserByName(String name) throws BusinessException {
		User user = null;
		if(name!=null) {
			user = userDAO.getUserByName(name);
		}
		return user;
	}

	@Override
	public User getUserById(int userId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) throws BusinessException {
		User newUser = null;
		UserDAO userdao = new UserDAOImpl();
		if(user.getUserName()!=null ) {
			newUser= userdao.createUser(user);
		}else {
			throw new BusinessException("Try another username");
		}
		
		return newUser;
	}
		

	@Override
	public List<User> getAllUsers() throws BusinessException {
		UserDAO userdao = new UserDAOImpl();
		List<User> userList = new ArrayList<>();
		userList = userdao.getAllUsers();
		return userList;
	}

	@Override
	public void deleteUser(int userId) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	

}
