package com.ssafy.ermp.model.dto;

public class Routine {
	private int logId;
	private String userId;
	private String day;
	private String type;
	private int goalAmount;
	private int achieveAmount;
	private String unit;
	
	public Routine() {
	}

	public Routine(int logId, String userId, String day, String type, int goalAmount, int achieveAmount, String unit) {
		this.logId = logId;
		this.userId = userId;
		this.day = day;
		this.type = type;
		this.goalAmount = goalAmount;
		this.achieveAmount = achieveAmount;
		this.unit = unit;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(int goalAmount) {
		this.goalAmount = goalAmount;
	}

	public int getAchieveAmount() {
		return achieveAmount;
	}

	public void setAchieveAmount(int achieveAmount) {
		this.achieveAmount = achieveAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Routine [logId=" + logId + ", userId=" + userId + ", day=" + day + ", type=" + type + ", goalAmount="
				+ goalAmount + ", achieveAmount=" + achieveAmount + ", unit=" + unit + "]";
	}

}
