package com.ssafy.ermp.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ermp.model.dto.Routine;

public interface RoutineDao {

	List<Routine> SelectRoutines(Map<String, String> map);
	
}
