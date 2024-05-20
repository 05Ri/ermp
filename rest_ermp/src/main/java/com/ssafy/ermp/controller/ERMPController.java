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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@RequestMapping("/ermp-api")
@Tag(name="ERMP Rest Controller", description="ERMP 기능들 CRUD")
public class ERMPController {
	@Autowired
	UserService uService;

	@Autowired
	RoutineService rService;
	
	@Autowired
	AttendanceService aService;
	
	
	////////// 회원 //////////
	
	@PostMapping("/user")
	@Operation(summary = "회원 로그인", description = "회원의 아이디와 비밀번호로 로그인")
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println(user);
		User loginUser = uService.login(user);
		System.out.println(loginUser);
		return new ResponseEntity<User>(loginUser, HttpStatus.OK) ;
	}
	
	@PostMapping("/user/regist")
	@Operation(summary = "회원가입", description = "ID, PW, 이름, 이메일로 가입 가능")
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
	@Operation(summary = "출석 체크", description = "로그인 시 오늘 날짜로 바로 출석체크시킵니다.")
	public ResponseEntity<?> attendanceCheck(@RequestBody User user) {
		String userId = user.getUserId();
		System.out.println(userId);
		if (aService.check(userId))
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 출석 체크 리스트 가져오기
	@GetMapping("/attendance")
	@Operation(summary = "출석 체크 리스트 가져오기", description = "달력에 표시하기 위해 출석체크한 날을 가져옵니다.")
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
	@Operation(summary = "루틴 가져오기", description = "사용자가 선택한 날의 루틴을 가져옵니다.")
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
	@Operation(summary = "루틴 추가", description = "사용자가 입력한 데이터들을 가져와서 모두 추가시킵니다.")
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
	@Operation(summary = "루틴 수정", description = "사용자가 루틴을 추가할 때의 데이터를 수정합니다.")
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
	@Operation(summary = "루틴 삭제", description = "사용자가 선택한 루틴 하나를 삭제합니다")
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
	@Operation(summary = "루틴 완료", description = "사용자가 달성한 수치를 입력 후 완료 버튼을 누르면 그 달성한 수치를 DB에 저장한다.")
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
	@Operation(summary = "통계 데이터 수집 후 보내기", description = "시작 날짜와 끝 날짜를 기준으로 사용자가 달성한 운동 이름과 달성 수치, 달성한 날들을 모두 보내준다.")
	public ResponseEntity<?> getAcheieveAmount(@RequestParam("userId") String userId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<Routine> routineList = rService.getAcheieveRoutineList(userId, startDate, endDate);
		return new ResponseEntity<List<Routine>>(routineList, HttpStatus.OK);
	}
}
