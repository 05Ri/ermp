package com.ssafy.ermp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ermp.model.dto.User;
import com.ssafy.ermp.model.service.ExerciseLogService;
import com.ssafy.ermp.model.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/ermp-api")
public class ERMPController {
	@Autowired
	UserService uService;

	@Autowired
	ExerciseLogService eService;
	
	
	@PostMapping("/user")
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println(user);
		User loginUser = uService.login(user);
		System.out.println(loginUser);
		return new ResponseEntity<User>(loginUser, HttpStatus.OK) ;
	}
	
	@PostMapping("/user/regist")
	public ResponseEntity<?> regist(@RequestBody User user) {
		if (uService.checkId(user.getUserId()))
			return new ResponseEntity<String>("아이디 중복",HttpStatus.BAD_REQUEST);
		if (uService.checkEmail(user.getEmail()))
			return new ResponseEntity<String>("이메일 중복",HttpStatus.BAD_REQUEST);
		uService.regist(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED) ;
	}
}
