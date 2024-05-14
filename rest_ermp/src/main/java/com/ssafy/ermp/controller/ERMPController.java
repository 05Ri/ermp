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
	
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println(user);
		User loginUser = uService.login(user);
		System.out.println(loginUser);
		return new ResponseEntity<User>(loginUser, HttpStatus.OK) ;
	}
}
