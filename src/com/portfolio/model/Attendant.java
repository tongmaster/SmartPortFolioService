package com.portfolio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Attendant {
	private int attendantId;
	private String attendantCode;
	private String attendantEmail;
	private String attendantPassword;
	private String attendantFirstName;
	private String attendantLastName;
	private String attendantUniversity;
	
	
	public String getAttendantUniversity() {
		return attendantUniversity;
	}
	public void setAttendantUniversity(String attendantUniversity) {
		this.attendantUniversity = attendantUniversity;
	}
	public int getAttendantId() {
		return attendantId;
	}
	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}
	public String getAttendantCode() {
		return attendantCode;
	}
	public void setAttendantCode(String attendantCode) {
		this.attendantCode = attendantCode;
	}
	public String getAttendantEmail() {
		return attendantEmail;
	}
	public void setAttendantEmail(String attendantEmail) {
		this.attendantEmail = attendantEmail;
	}
	public String getAttendantPassword() {
		return attendantPassword;
	}
	public void setAttendantPassword(String attendantPassword) {
		this.attendantPassword = attendantPassword;
	}
	public String getAttendantFirstName() {
		return attendantFirstName;
	}
	public void setAttendantFirstName(String attendantFirstName) {
		this.attendantFirstName = attendantFirstName;
	}
	public String getAttendantLastName() {
		return attendantLastName;
	}
	public void setAttendantLastName(String attendantLastName) {
		this.attendantLastName = attendantLastName;
	}
	
}
