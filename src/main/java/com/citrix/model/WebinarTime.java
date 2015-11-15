package com.citrix.model;

public class WebinarTime {

	private String startTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime_AMPM() {
		return startTime_AMPM;
	}
	public void setStartTime_AMPM(String startTime_AMPM) {
		this.startTime_AMPM = startTime_AMPM;
	}
	public String getEndTime_AMPM() {
		return endTime_AMPM;
	}
	public void setEndTime_AMPM(String endTime_AMPM) {
		this.endTime_AMPM = endTime_AMPM;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	

	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	private String endTime;
	private String startTime_AMPM;
	private String endTime_AMPM;
	private String startYear;
	private String startMonth;
	private String startDay;
	private String timeZone;
}
