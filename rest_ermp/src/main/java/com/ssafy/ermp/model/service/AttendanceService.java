package com.ssafy.ermp.model.service;

import java.util.List;

public interface AttendanceService {

	List<String> getList(String userId);

	boolean check(String userId);

}
