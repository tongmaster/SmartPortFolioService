package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portfolio.model.Attendant;
import com.portfolio.model.Diagnosis;
import com.portfolio.model.Message;
import com.portfolio.util.ConnectionHelper;



public class DiagnosisDao {
	public Message<Diagnosis> insertDiagnosis(Diagnosis diagnosis) throws Exception {
		
		System.out.println("on method insertDiagnosis() of Diagnosis table");
		Connection conn = null;
		Message<Diagnosis> message = new Message<Diagnosis>();
		try {
			String sqlInsert = "insert into diagnosis (patient_first_name, patient_last_name, symptoms , diagnosis ,treatment"
					+ " ,confidence_score ,diagnosis_datetime ,diagnosis_date ,diagnosis_time ,md_pro_id ,create_date ,attendant_code ) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			java.util.Date date= new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    System.out.println(timestamp);
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, diagnosis.getPatientFirstName());
			insert.setString(2, diagnosis.getPatientLastName());
			insert.setString(3, diagnosis.getSymptoms());
			insert.setString(4, diagnosis.getDiagnosis());
			insert.setString(5, diagnosis.getTreatment());
			insert.setString(6, diagnosis.getConfidenceScore());
			insert.setTimestamp(7, timestamp);
			insert.setString(8, diagnosis.getDiagnosisDate());
			insert.setString(9, diagnosis.getDiagnosisTime());
			insert.setString(10, diagnosis.getMdProId());
		
			
			insert.setTimestamp(11, new Timestamp(date.getTime()));
			insert.setString(12, diagnosis.getAttendantCode());
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertDiagnosis Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertDiagnosis Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertDiagnosis Complete  =====================");

			}
			insert.close();
		} catch (Exception e) {
			e.printStackTrace();
			String tableKey = e.getMessage();
			throw new Exception(
					"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
		}
		conn.close();
		return message;
	}


}
