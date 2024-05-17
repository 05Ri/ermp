package com.ssafy.ermp.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ermp.model.dto.Routine;

public interface RoutineDao {

	List<Routine> selectRoutines(Map<String, String> map);
	
	void insertRoutine(Routine routine);

	void updateRoutine(Routine routine);

	void deleteRoutine(int logId);
	
}
