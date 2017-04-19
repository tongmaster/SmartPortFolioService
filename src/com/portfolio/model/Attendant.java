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
	private String pic;
	private String deadline1;
	//private String deadline2;
	
	
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDeadline1() {
		return deadline1;
	}
	public void setDeadline1(String deadline1) {
		this.deadline1 = deadline1;
	}
/*	public String getDeadline2() {
		return deadline2;
	}
	public void setDeadline2(String deadline2) {
		this.deadline2 = deadline2;
	}
	*/
	
	
	
}
