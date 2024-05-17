package com.ssafy.ermp.model.dao;

import java.util.List;

import com.ssafy.ermp.model.dto.Routine;

public interface RoutineDao {

	List<Routine> SelectRoutines(String userId);
	
}
