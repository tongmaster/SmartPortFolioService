package com.portfolio.model;
import java.sql.Timestamp;
public class Diagnosis {
	private String diagnosisDate;
	private String diagnosisTime;
	private Timestamp diagnosisDateTime;
	private String mdProId;
	private String attendantCode;
	private String patientFirstName;
	private String patientLastName;
	private String symptoms;
	private String diagnosis;
	private String treatment;
	private String confidenceScore;
	private String studentCode;
	private String status;
	private Timestamp createDate;
	private String deadlineDate;
	private String dignosisSuggestion;
	private int diagnosisId;
	private int rating;
	private String confidenceDetail;
	private String isRead;
	
	
	
	
	
	
	
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getConfidenceDetail() {
		return confidenceDetail;
	}
	public void setConfidenceDetail(String confidenceDetail) {
		this.confidenceDetail = confidenceDetail;
	}
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public String getAttendantCode() {
		return attendantCode;
	}
	public void setAttendantCode(String attendantCode) {
		this.attendantCode = attendantCode;
	}
	public String getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public String getDiagnosisTime() {
		return diagnosisTime;
	}
	public void setDiagnosisTime(String diagnosisTime) {
		this.diagnosisTime = diagnosisTime;
	}
	public String getMdProId() {
		return mdProId;
	}
	public void setMdProId(String mdProId) {
		this.mdProId = mdProId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getConfidenceScore() {
		return confidenceScore;
	}
	public void setConfidenceScore(String confidenceScore) {
		this.confidenceScore = confidenceScore;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getDiagnosisDateTime() {
		return diagnosisDateTime;
	}
	public void setDiagnosisDateTime(Timestamp diagnosisDateTime) {
		this.diagnosisDateTime = diagnosisDateTime;
	}

	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDignosisSuggestion() {
		return dignosisSuggestion;
	}
	public void setDignosisSuggestion(String dignosisSuggestion) {
		this.dignosisSuggestion = dignosisSuggestion;
	}
	
	
}
