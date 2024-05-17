package com.ssafy.ermp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ermp.model.dao.AttendanceDao;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceDao dao;
	
	@Override
	public boolean check(String userId) {
		try {
			dao.insertAttendance(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<String> getList(String userId) {
		return dao.getAttendanceDateList(userId);
	}

	
	
}
