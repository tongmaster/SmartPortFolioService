package com.portfolio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Advisor {
	private int advisorId;
	private String advisorCode;
	private String advisorEmail;
	private String advisorPassword;
	private String advisorFirstName;
	private String advisorLastName;
	private String advisorUniversity;
	
	
	public String getAdvisorUniversity() {
		return advisorUniversity;
	}
	public void setAdvisorUniversity(String advisorUniversity) {
		this.advisorUniversity = advisorUniversity;
	}
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	public String getAdvisorCode() {
		return advisorCode;
	}
	public void setAdvisorCode(String advisorCode) {
		this.advisorCode = advisorCode;
	}
	public String getAdvisorEmail() {
		return advisorEmail;
	}
	public void setAdvisorEmail(String advisorEmail) {
		this.advisorEmail = advisorEmail;
	}
	public String getAdvisorPassword() {
		return advisorPassword;
	}
	public void setAdvisorPassword(String advisorPassword) {
		this.advisorPassword = advisorPassword;
	}
	public String getAdvisorFirstName() {
		return advisorFirstName;
	}
	public void setAdvisorFirstName(String advisorFirstName) {
		this.advisorFirstName = advisorFirstName;
	}
	public String getAdvisorLastName() {
		return advisorLastName;
	}
	public void setAdvisorLastName(String advisorLastName) {
		this.advisorLastName = advisorLastName;
	}
	
}
