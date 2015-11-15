package com.citrix.model;



public class Webinar {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public Occurance getOccurance() {
		return occurance;
	}
	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	private String name;
	private String description;
	private String startTime;
	private String endTime;
	private String startTime_AMPM;
	private String endTime_AMPM;
	private Occurance occurance;
	private String startYear;
	private String startMonth;
	private String startDay;
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	private String day;
	private String timeZone;
	private String language;

}
