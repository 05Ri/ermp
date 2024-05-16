package com.ssafy.ermp.model.dao;

import java.util.List;

public interface AttendanceDao {
	
	void insertAttendance(String userId);
	
	List<String> getAttendanceDateList(String userId);

}
