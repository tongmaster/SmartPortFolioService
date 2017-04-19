package com.portfolio.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Admin {
	private int id;
	private String username;
	private String password;
	private String email;
	private Timestamp createDate;
	private String status;
	private String member;
	private String university;
	private String dignosis;
	private String knowlege;
	private String medicine;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getDignosis() {
		return dignosis;
	}
	public void setDignosis(String dignosis) {
		this.dignosis = dignosis;
	}
	public String getKnowlege() {
		return knowlege;
	}
	public void setKnowlege(String knowlege) {
		this.knowlege = knowlege;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	
	
	
}
	
	
	