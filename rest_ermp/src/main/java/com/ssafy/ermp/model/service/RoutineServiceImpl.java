package com.ssafy.ermp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ermp.model.dao.RoutineDao;
import com.ssafy.ermp.model.dto.Routine;

@Service
public class RoutineServiceImpl implements RoutineService {

	@Autowired
	RoutineDao dao;
	
	@Override
	public List<Routine> getRoutines(String userId) {
		return dao.SelectRoutines(userId);
	}

}
