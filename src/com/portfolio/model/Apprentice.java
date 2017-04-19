package com.portfolio.model;

public class Apprentice {
	private String apprenticeStartDate;
	private String apprenticeEndDate;
	private String studentCode;
	private String attendantCode;
	private String createDate;
	
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getApprenticeStartDate() {
		return apprenticeStartDate;
	}
	public void setApprenticeStartDate(String apprenticeStartDate) {
		this.apprenticeStartDate = apprenticeStartDate;
	}
	public String getApprenticeEndDate() {
		return apprenticeEndDate;
	}
	public void setApprenticeEndDate(String apprenticeEndDate) {
		this.apprenticeEndDate = apprenticeEndDate;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getAttendantCode() {
		return attendantCode;
	}
	public void setAttendantCode(String attendantCode) {
		this.attendantCode = attendantCode;
	}
	
}
