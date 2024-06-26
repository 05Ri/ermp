package com.ssafy.ermp.model.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
	public void modifyRoutine(Routine routine) {
		dao.updateRoutine(routine);
	}


	@Override
	public void addRoutines(List<Routine> routines) {
		for(Routine routine: routines) {
			addRoutines(routine);
		}
	}
	
	public void addRoutines(Routine routine) {
		LocalDate curr = LocalDate.parse(routine.getStartDate());
		LocalDate end = LocalDate.parse(routine.getEndDate());
		while(curr.compareTo(end)<=0) {
			routine.setDay(curr.toString());
			dao.insertRoutine(routine);
			curr = curr.plus(routine.getPeriod(), ChronoUnit.DAYS);
		}
	}

	@Override
	public void deleteRoutine(int logId) {
		dao.deleteRoutine(logId);
	}

	@Override
	public void completeRoutine(int logId, int achieveAmount) {
		Map<String, Integer> map = new HashMap<>();
		map.put("logId", logId);
		map.put("achieveAmount", achieveAmount);		
		dao.updateAchieveAmount(map);
	}

	@Override
	public List<Routine> getAcheieveRoutineList(String userId, String startDate, String endDate) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return dao.selectAchiveRoutineList(map);
	}
	
	


}
