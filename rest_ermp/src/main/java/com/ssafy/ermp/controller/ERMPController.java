package com.ssafy.ermp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ermp.model.dto.Routine;
import com.ssafy.ermp.model.dto.User;
import com.ssafy.ermp.model.service.AttendanceService;
import com.ssafy.ermp.model.service.RoutineService;
import com.ssafy.ermp.model.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/ermp-api")
public class ERMPController {
	@Autowired
	UserService uService;

	@Autowired
	RoutineService rService;
	
	@Autowired
	AttendanceService aService;
	
	
	////////// 회원 //////////
	
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
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	////////// 출석 체크 //////////
	
	// 출석 체크 하기
	@PostMapping("/attendance")
	public ResponseEntity<?> attendanceCheck(@RequestBody User user) {
		String userId = user.getUserId();
		System.out.println(userId);
		if (aService.check(userId))
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 출석 체크 리스트 가져오기
	@GetMapping("/attendance")
	public ResponseEntity<?> getAttendanceDayList(@RequestParam("userId") String userId) {
		List<String> attendanceDayList = aService.getList(userId);
		return new ResponseEntity<List<String>>(attendanceDayList, HttpStatus.OK);
	}
	
	
	//////////// 루틴 //////////
	

	/** 루틴 가져오기
	 * params : 
	 * {
	 * 		"userId": "userId"
	 * 		"day": "2024-05-17" 
	 * }
	 * (쿼리스트링)
	 */
	@GetMapping("/routine")
	public ResponseEntity<?> getRoutines(@RequestParam("userId") String userId, @RequestParam("day") String day) {
		List<Routine> routines = rService.getRoutines(userId, day);
		return new ResponseEntity<List<Routine>>(routines, HttpStatus.OK);
	}
	
	/** 루틴 추가
	 * params : 
	 * {
	 * 	  "userId": "ssafy",
	 *    "type": "복근",
	 *	  "goalAmount": 30,
	 *	  "unit": "회",
	 *	  "startDate": "2024-05-17",
	 *	  "endDate": "2024-05-31",
	 *	  "period": 3
	 *	}
	 */
	@PostMapping("/routine")
	public ResponseEntity<?> addRoutines(@RequestBody List<Routine> routine) {
		rService.addRoutines(routine);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/** 루틴 수정
	 * params : 
	 * {
	 * 	  "logId": 4,
	 *    "type": "복근",
	 *	  "goalAmount": 30,
	 *	  "unit": "회",
	 *  }
	 */
	@PutMapping("/routine")
	public ResponseEntity<?> modifyRoutine(@RequestBody Routine routine) {
		rService.modifyRoutine(routine);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/** 루틴 삭제
	 * params : 
	 * {
	 * 	  "logId": 4
	 *  }
	 */
	@DeleteMapping("/routine")
	public ResponseEntity<?> deleteRoutine(@RequestBody Routine routine) {
		rService.deleteRoutine(routine.getLogId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/** 루틴 완료 체크
	 * params : 
	 * {
	 * 	  "logId": 4,
	 *	  "achieveAmount": 30,
	 *  }
	 */
	@PutMapping("/routine/check")
	public ResponseEntity<?> completeRoutine(@RequestBody Routine routine) {
		rService.completeRoutine(routine.getLogId(), routine.getAchieveAmount());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/** 통계 시작날 ~ 끝날
	 * params:
	 * {
	 * 		"userId": "ssafy",
	 *		"startDate": "2024-04-30",
	 *		"endDate": "2024-05-14"
	 * }
	 * 
	 * return: [day, type, goalAmount] 
	 */
	@GetMapping("/statistics")
	public ResponseEntity<?> getAcheieveAmount(@RequestBody Routine routine) {
		List<Routine> routineList = rService.getAcheieveRoutineList(routine.getUserId(), routine.getStartDate(), routine.getEndDate());
		return new ResponseEntity<List<Routine>>(routineList, HttpStatus.OK);
	}
}
