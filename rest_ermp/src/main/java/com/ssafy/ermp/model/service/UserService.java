package com.ssafy.ermp.model.service;

import com.ssafy.ermp.model.dto.User;

public interface UserService {

	User login(User user);
	
	void regist(User user);

	boolean checkId(String userId);

	boolean checkEmail(String email);
}