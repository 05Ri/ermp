package com.ssafy.ermp.model.dto;

import java.util.Date;

public class ExerciseLog {
	private String userId;
	private String type;
	private Date day;
	private int amount;
	private String unit;
	
	public ExerciseLog() {
	}

	public ExerciseLog(String userId, String type, Date day, int amount, String unit) {
		this.userId = userId;
		this.type = type;
		this.day = day;
		this.amount = amount;
		this.unit = unit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "ExerciseLog [userId=" + userId + ", type=" + type + ", day=" + day + ", amount=" + amount + ", unit="
				+ unit + "]";
	}
}
