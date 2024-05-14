package com.ssafy.ermp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ermp.model.dao.UserDao;
import com.ssafy.ermp.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;
	
	@Override
	public User login(User user) {
		return dao.selectUser(user);
	}
	
	public void regist(User user) {
		dao.insertUser(user);
	}

	@Override
	public boolean checkId(String userId) {
		return dao.checkId(userId) > 0; 
	}

	@Override
	public boolean checkEmail(String email) {
		return dao.checkId(email) >  0; 
	}

}
