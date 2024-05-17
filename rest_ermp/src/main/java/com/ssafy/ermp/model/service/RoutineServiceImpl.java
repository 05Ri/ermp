package com.ssafy.ermp.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ermp.model.dao.RoutineDao;
import com.ssafy.ermp.model.dto.Routine;

@Service
public class RoutineServiceImpl implements RoutineService {

	@Autowired
	RoutineDao dao;

	@Override
	public List<Routine> getRoutines(String userId, String day) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("day", day);
		return dao.selectRoutines(map);
	}

	@Override
	public void ModifyRoutine(int logId) {
		
	}

	@Override
	public void addRoutines(Routine routine) {
		
	}

}
