package com.zw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zw.dao.IUserDao;
import com.zw.model.User;
import com.zw.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		return userDao.queryByPrimaryKey(userId);
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void addUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
}
