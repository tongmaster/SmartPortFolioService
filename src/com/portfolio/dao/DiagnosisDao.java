package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.portfolio.model.Diagnosis;
import com.portfolio.model.DiagnosisResult;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class DiagnosisDao {
	public Message<Diagnosis> insertDiagnosis(Diagnosis diagnosis) throws Exception {
		
		System.out.println("on method insertDiagnosis() of Diagnosis table");
		Connection conn = null;
		Message<Diagnosis> message = new Message<Diagnosis>();
		try {
			String sqlInsert = "insert into diagnosis (patient_first_name, patient_last_name, symptoms , diagnosis ,treatment"
					+ " ,confidence_score ,diagnosis_datetime ,diagnosis_date ,diagnosis_time ,md_pro_id ,create_date ,attendant_code "
					+ " ,student_code ,status, deadline_date , approve , approve_date , confidence_detail , isRead) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(diagnosis.getDiagnosisDate());
		    Date parsedDate = dateFormat.parse(diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		    System.out.println(">>"+parsedDate);
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
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String a = diagnosis.getDiagnosisDate().substring(0, 4);
			String b = (Integer.parseInt(a)+543)+"";
			String c = diagnosis.getDiagnosisDate().substring(4);
			insert.setString(8, diagnosis.getDiagnosisDate());
			insert.setString(9, diagnosis.getDiagnosisTime());
			insert.setString(10, diagnosis.getMdProId());
			
		
			
			insert.setTimestamp(11, new Timestamp(date.getTime()));
			insert.setString(12, diagnosis.getAttendantCode());
			insert.setString(13, diagnosis.getStudentCode());
			insert.setString(14, diagnosis.getStatus());
			
			//SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			 Date parsedDate1 = dateFormat2.parse(diagnosis.getDeadlineDate());
			 java.sql.Timestamp sql = new java.sql.Timestamp(parsedDate1.getTime());
			insert.setTimestamp(15, sql);
			insert.setString(16, "N");

			java.util.Date today = new java.util.Date();
			
			insert.setTimestamp(17, new java.sql.Timestamp(today.getTime()));
			insert.setString(18, diagnosis.getConfidenceDetail());
			insert.setString(19, "D");
			//insert.setNull(16, java.sql.Types.TIMESTAMP);
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println(insert);
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
	
	public Message<Diagnosis> insertRating(int diagnosisId,int rating,String dignosisSuggestion) throws Exception {
		
		System.out.println("on method insertDiagnosis() of Diagnosis table");
		Connection conn = null;
		Message<Diagnosis> message = new Message<Diagnosis>();
		try {
			String sqlInsert = "update diagnosis set dignosis_suggestion = ? , rating = ? , approve = 'Y' ,approve_date = now() , isRead = 'N' "
					+ " where diagnosis_id = ?";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
		/*	Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(diagnosis.getDiagnosisDate());
		    Date parsedDate = dateFormat.parse(diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		    System.out.println(">>"+parsedDate);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    System.out.println(timestamp);*/
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1,dignosisSuggestion);
			insert.setInt(2, rating);
			insert.setInt(3, diagnosisId);
	
			
		
			
		
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println(insert);
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertDiagnosis sign Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertDiagnosis sign Completed");
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
	
	
public Message<Diagnosis> updateReadDiagnose(int diagnosisId) throws Exception {
		
		System.out.println("on method updateReadDiagnose() of Diagnosis table");
		Connection conn = null;
		Message<Diagnosis> message = new Message<Diagnosis>();
		try {
			String sqlInsert = "update diagnosis set isRead = 'Y' "
					+ " where diagnosis_id = ? and approve = 'Y' ";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
		/*	Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(diagnosis.getDiagnosisDate());
		    Date parsedDate = dateFormat.parse(diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		    System.out.println(">>"+parsedDate);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    System.out.println(timestamp);*/
			insert = conn.prepareStatement(sqlInsert);
			insert.setInt(1,diagnosisId);
	
			
		
			
		
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println(insert);
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("updateReadDiagnose  read Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("updateReadDiagnose read Completed");
				message.setStatusCode("0");
				System.out.println("=================== End updateReadDiagnose Complete  =====================");

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
	
public Message<DiagnosisResult> getDiagonosis(String attendantCode, String studentCode) throws Exception {
		
		System.out.println("on method getDiagonosis() of diagonosis , student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<DiagnosisResult> message = new Message<DiagnosisResult>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
		try {		
			String sql = "select d.*,s.student_first_name, s.student_last_name ,s.pic , mp.md_pro_name  from  diagnosis d join student s "+
				" on d.student_code = s.student_code "
				+ "join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id "
				+ "where  d.student_code = ? and d.attendant_code = ?   ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, studentCode);
			stm.setString(2, attendantCode);
			rs  = stm.executeQuery();
			DiagnosisResult stud = null;
			List<DiagnosisResult> Studentlist = new ArrayList<DiagnosisResult>();
			while (rs.next())
			{
				stud = new DiagnosisResult();
				stud.setDiagnosisId(rs.getInt("diagnosis_id"));
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				stud.setAttendantCode(rs.getString("attendant_code"));
				stud.setConfidenceScore(rs.getString("confidence_score"));
				String deadline = format.format(rs.getDate("deadline_date"));
				stud.setDeadlineDate(deadline);
				stud.setDiagnosis(rs.getString("diagnosis"));
				stud.setDiagnosisDate(rs.getString("diagnosis_date"));
				stud.setDiagnosisTime(rs.getString("diagnosis_time"));
				stud.setMdProName(rs.getString("md_pro_name"));
				stud.setPatientFirstName(rs.getString("patient_first_name"));
				stud.setPatientLastName("patient_last_name");
				stud.setRating(rs.getInt("rating"));
				stud.setStatus(rs.getString("status"));
				stud.setSymptoms(rs.getString("symptoms"));
				stud.setTreatment(rs.getString("treatment"));
				stud.setDignosisSuggestion(rs.getString("dignosis_suggestion"));
				stud.setApprove(rs.getString("approve"));
				stud.setApproveDate(rs.getTimestamp("approve_date"));
				stud.setConfidenceDetail(rs.getString("confidence_detail"));
				stud.setIsRead(rs.getString("isRead"));
				stud.setPic(rs.getString("pic"));
				
		
				message.setStatusCode("0");
				message.setStatusMsg("DiagnosisResult found");
				Studentlist.add(stud);
				message.setList(Studentlist);
			}
			
			if(Studentlist == null || Studentlist.size() == 0) 
			{
				message.setStatusCode("1");
				message.setStatusMsg("no diagonosis for this attendant");
			}
			
			conn.close();
			rs.close();
			
	
		} catch (Exception e) {
			e.printStackTrace();
			String tableKey = e.getMessage();
			throw new Exception(
					"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
		}
		conn.close();
		return message;
	}


public Message<DiagnosisResult> getDiagonosisById(int digonosisId) throws Exception {
	
	System.out.println("on method getDiagonosisById() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
	try {		
		String sql = "select d.*,s.student_first_name, s.student_last_name , s.pic , mp.md_pro_name  from  diagnosis d join student s "+
			" on d.student_code = s.student_code "
			+ "join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id "
			+ "where  d.diagnosis_id = ?   ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setInt(1, digonosisId);
		rs  = stm.executeQuery();
		DiagnosisResult stud = null;
		List<DiagnosisResult> Studentlist = new ArrayList<DiagnosisResult>();
		while (rs.next())
		{
			stud = new DiagnosisResult();
			stud.setDiagnosisId(rs.getInt("diagnosis_id"));
			stud.setStudentCode(rs.getString("student_code"));
			stud.setStudentFirstName(rs.getString("student_first_name"));
			stud.setStudentLastName(rs.getString("student_last_name"));
			stud.setAttendantCode(rs.getString("attendant_code"));
			stud.setConfidenceScore(rs.getString("confidence_score"));
			String deadline = format.format(rs.getDate("deadline_date"));
			stud.setDeadlineDate(deadline);
			stud.setDiagnosis(rs.getString("diagnosis"));
			stud.setDiagnosisDate(rs.getString("diagnosis_date"));
			stud.setDiagnosisTime(rs.getString("diagnosis_time"));
			stud.setMdProName(rs.getString("md_pro_name"));
			stud.setPatientFirstName(rs.getString("patient_first_name"));
			stud.setPatientLastName(rs.getString("patient_last_name"));
			stud.setRating(rs.getInt("rating"));
			stud.setStatus(rs.getString("status"));
			stud.setSymptoms(rs.getString("symptoms"));
			stud.setTreatment(rs.getString("treatment"));
			stud.setDignosisSuggestion(rs.getString("dignosis_suggestion"));
			stud.setConfidenceDetail(rs.getString("confidence_detail"));
			stud.setApprove(rs.getString("approve"));
			stud.setIsRead(rs.getString("isRead"));
			stud.setApproveDate(rs.getTimestamp("approve_date"));
			stud.setPic(rs.getString("pic"));
			message.setStatusCode("0");
			message.setStatusMsg("getRating found");
			Studentlist.add(stud);
			message.setList(Studentlist);
		}
		
		if(Studentlist == null || Studentlist.size() == 0) 
		{
			message.setStatusCode("1");
			message.setStatusMsg("no getRating for this attendant");
		}
		
		conn.close();
		rs.close();
		

	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return message;
}



public int getGraphReportDataSend(int month , int year, String attendantCode ,String universityCode) throws Exception {
	
	System.out.println("on method getGraphReportDataSend() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	int amount = 0 ;
	try {		
		String sql = "select count(*) amount  "+
			"from  diagnosis d join student s "+
			" on d.student_code = s.student_code "+
			" where  month(d.diagnosis_datetime) = ? and year(d.diagnosis_datetime) = ?   ";
		if(!attendantCode.equals("") && !attendantCode.equals("A"))
			sql += " and d.attendant_code = '"+attendantCode+"' ";
		if(!universityCode.equals("") && !universityCode.equals("A"))
			sql += " and s.student_university = '"+universityCode+"' ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setInt(1, month);
		stm.setInt(2, year);
		rs  = stm.executeQuery();
		if (rs.next())
		{
			amount = rs.getInt("amount"); 
		}
		conn.close();
		rs.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return amount;
}

public int getGraphReportDataRead(int month , int year, String attendantCode ,String universityCode) throws Exception {
	
	System.out.println("on method getGraphReportDataSend() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	int amount = 0 ;
	try {		
		String sql = "select count(*) amount  "+
			"from  diagnosis d join student s "+
			" on d.student_code = s.student_code "+
			" where  month(d.diagnosis_datetime) = ? and year(d.diagnosis_datetime) = ?  and d.isRead = 'Y'  ";
		if(!attendantCode.equals("") && !attendantCode.equals("A"))
			sql += " and d.attendant_code = '"+attendantCode+"' ";
		if(!universityCode.equals("") && !universityCode.equals("A"))
			sql += " and s.student_university = '"+universityCode+"' ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setInt(1, month);
		stm.setInt(2, year);
		rs  = stm.executeQuery();
		if (rs.next())
		{
			amount = rs.getInt("amount"); 
		}
		conn.close();
		rs.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return amount;
}


public int getGraphReportDataInspect(int month , int year, String attendantCode ,String universityCode) throws Exception {
	
	System.out.println("on method getGraphReportDataSend() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	int amount = 0 ;
	try {		
		String sql = "select count(*) amount  "+
			"from  diagnosis d join student s "+
			" on d.student_code = s.student_code "+
			" where  month(d.diagnosis_datetime) = ? and year(d.diagnosis_datetime) = ?  and d.approve = 'Y'  ";
		if(!attendantCode.equals("") && !attendantCode.equals("A"))
			sql += " and d.attendant_code = '"+attendantCode+"' ";
		if(!universityCode.equals("") && !universityCode.equals("A"))
			sql += " and s.student_university = '"+universityCode+"' ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setInt(1, month);
		stm.setInt(2, year);
		rs  = stm.executeQuery();
		if (rs.next())
		{
			amount = rs.getInt("amount"); 
		}
		conn.close();
		rs.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return amount;
}
public static void main(String[] args) {
	DiagnosisDao a =  new DiagnosisDao();
	try {
		int b = a.getGraphReportDataSend(6, 2016, "A", "A");
		int c = a.getGraphReportDataRead(6, 2016, "A", "A");
		int d = a.getGraphReportDataInspect(6, 2016, "A", "A");
		System.out.println(b +"  "+c+"   "+d);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	/*
public Message<DiagnosisResult> getDiagonosis(String studentCode) throws Exception {
		
		System.out.println("on method getDiagonosis() of diagonosis , student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<DiagnosisResult> message = new Message<DiagnosisResult>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
		try {		
			String sql = "select d.*,s.student_first_name, s.student_last_name , mp.md_pro_name  from  diagnosis d join student s "+
				" on d.student_code = s.student_code "
				+ "join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id "
				+ "where d.student_code = ?   ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, studentCode);
			//stm.setString(2, attendantCode);
			rs  = stm.executeQuery();
			DiagnosisResult stud = null;
			List<DiagnosisResult> Studentlist = new ArrayList<DiagnosisResult>();
			while (rs.next())
			{
				stud = new DiagnosisResult();
				stud.setDiagnosisId(rs.getInt("diagnosis_id"));
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				stud.setAttendantCode(rs.getString("attendant_code"));
				stud.setConfidenceScore(rs.getString("confidence_score"));
				String deadline = format.format(rs.getDate("deadline_date"));
				stud.setDeadlineDate(deadline);
				stud.setDiagnosis(rs.getString("diagnosis"));
				stud.setDiagnosisDate(rs.getString("diagnosis_date"));
				stud.setDiagnosisTime(rs.getString("diagnosis_time"));
				stud.setMdProName(rs.getString("md_pro_name"));
				stud.setPatientFirstName(rs.getString("patient_first_name"));
				stud.setPatientLastName("patient_last_name");
				stud.setRating(rs.getInt("rating"));
				stud.setStatus(rs.getString("status"));
				stud.setSymptoms(rs.getString("symptoms"));
				stud.setTreatment(rs.getString("treatment"));
				stud.setDignosisSuggestion(rs.getString("dignosis_suggestion"));
				
		
				message.setStatusCode("0");
				message.setStatusMsg("DiagnosisResult found");
				Studentlist.add(stud);
				message.setList(Studentlist);
			}
			
			
			
			conn.close();
			rs.close();
			
	
		} catch (Exception e) {
			e.printStackTrace();
			String tableKey = e.getMessage();
			throw new Exception(
					"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
		}
		conn.close();
		return message;
	}


public Message<DiagnosisResult> getDiagonosisAll() throws Exception {
	
	System.out.println("on method getDiagonosis() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
	try {		
		String sql = "select d.*,s.student_first_name, s.student_last_name , mp.md_pro_name  from  diagnosis d join student s "+
			" on d.student_code = s.student_code "
			+ "join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id ";
			//+ "where  d.attendant_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		//stm.setString(1, attendantCode);
		rs  = stm.executeQuery();
		DiagnosisResult stud = null;
		List<DiagnosisResult> Studentlist = new ArrayList<DiagnosisResult>();
		while (rs.next())
		{
			stud = new DiagnosisResult();
			stud.setDiagnosisId(rs.getInt("diagnosis_id"));
			stud.setStudentCode(rs.getString("student_code"));
			stud.setStudentFirstName(rs.getString("student_first_name"));
			stud.setStudentLastName(rs.getString("student_last_name"));
			stud.setAttendantCode(rs.getString("attendant_code"));
			stud.setConfidenceScore(rs.getString("confidence_score"));
			String deadline = format.format(rs.getDate("deadline_date"));
			stud.setDeadlineDate(deadline);
			stud.setDiagnosis(rs.getString("diagnosis"));
			stud.setDiagnosisDate(rs.getString("diagnosis_date"));
			stud.setDiagnosisTime(rs.getString("diagnosis_time"));
			stud.setMdProName(rs.getString("md_pro_name"));
			stud.setPatientFirstName(rs.getString("patient_first_name"));
			stud.setPatientLastName("patient_last_name");
			stud.setRating(rs.getInt("rating"));
			stud.setStatus(rs.getString("status"));
			stud.setSymptoms(rs.getString("symptoms"));
			stud.setTreatment(rs.getString("treatment"));
			stud.setDignosisSuggestion(rs.getString("dignosis_suggestion"));
			
			
			message.setStatusCode("0");
			message.setStatusMsg("DiagnosisResult found");
			Studentlist.add(stud);
			message.setList(Studentlist);
		}
		
		
		
			//System.out.println("haveRow>>> false");
		conn.close();
		rs.close();
		

	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return message;
}
*/

}
