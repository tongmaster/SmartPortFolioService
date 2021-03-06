package com.portfolio.model;
import java.sql.Timestamp;
public class Diagnosis {
	private String diagnosisDate;
	private String diagnosisTime;
	private Timestamp diagnosisDateTime;
	private String mdProId;
	private String patientFirstName;
	private String patientLastName;
	private String symptoms;
	private String diagnosis;
	private String treatment;
	private String confidence_score;
	private Timestamp createDate;
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
	public String getConfidence_score() {
		return confidence_score;
	}
	public void setConfidence_score(String confidence_score) {
		this.confidence_score = confidence_score;
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
	
	
}
