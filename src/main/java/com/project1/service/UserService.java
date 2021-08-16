package com.project1.service;

import java.util.List;

import com.project1.exception.BusinessException;
import com.project1.model.User;

public interface UserService {
	
	public List<User> getUsersByName(String name) throws BusinessException;
	public User getUserByName(String name) throws BusinessException;
	public User getUserById(int userId)throws BusinessException;

	public User createUser(User user) throws BusinessException;
	public List<User> getAllUsers() throws BusinessException;
	public void deleteUser(int userId) throws BusinessException;

	
}
