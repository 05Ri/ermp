package com.ssafy.ermp.model.dao;

import com.ssafy.ermp.model.dto.User;

public interface UserDao {
	public User selectUser(User user);

	public void insertUser(User user);
	
	public int checkId(String userId);
	
	public int checkEmail(String email);
}
