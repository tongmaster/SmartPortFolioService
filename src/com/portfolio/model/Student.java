package com.portfolio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private int studentId;
	private String studentCode;
	private String studentEmail;
	private String studentPassword;
	private String studentFirstName;
	private String studentLastName;
	private String studentUniversity;
	private String apprenticeStartDate;
	private String apprenticeEndDate;
	private String attendantCode;
	private String amount;
	private String pic;
	
	
	
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAttendantCode() {
		return attendantCode;
	}
	public void setAttendantCode(String attendantCode) {
		this.attendantCode = attendantCode;
	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStudentUniversity() {
		return studentUniversity;
	}
	public void setStudentUniversity(String studentUniversity) {
		this.studentUniversity = studentUniversity;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
	
}
	
	
	