package com.ssafy.ermp.model.service;

import java.util.List;

import com.ssafy.ermp.model.dto.Routine;

public interface RoutineService {
	// 루틴 가져오기
	List<Routine> getRoutines(String userId, String day);
	
	// 루틴 수정
	Routine modifyRoutine(Routine routine);
	
	// 루틴 추가
	void addRoutines(List<Routine> routines);
}
