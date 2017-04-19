package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.portfolio.model.Attendant;
import com.portfolio.model.DiagnosisResult;
import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class AttendantDao {
	public Message<Attendant> insertAttendant(Attendant Attendant) throws Exception {
		
		System.out.println("on method insertAttendant() of Attendant table");
		Connection conn = null;
		Message<Attendant> message = new Message<Attendant>();
		try {
			String sqlInsert = "insert into attendant (attendant_code, attendant_email, attendant_password , attendant_first_name, attendant_last_name , attendant_university, pic) "
					+ " values (?,?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, Attendant.getAttendantCode());
			insert.setString(2, Attendant.getAttendantEmail());
			insert.setString(3, Attendant.getAttendantPassword());
			insert.setString(4, Attendant.getAttendantFirstName());
			insert.setString(5, Attendant.getAttendantLastName());
			insert.setString(6, Attendant.getAttendantUniversity());
			insert.setString(7, Attendant.getPic());
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertAttendant Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertAttendant Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertAttendant Complete  =====================");

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

	
public Message<Attendant> checkLogin(Attendant Attendant) throws Exception {
		
		System.out.println("on method checkLogin() of Attendant table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Attendant> message = new Message<Attendant>();
		try {		
			String sql = "select *  "+
				" from attendant  where (? is null OR (attendant_code = ?)) AND (? is null OR (attendant_email = ?)) and attendant_password = ?  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			
			if(!("").equals(Attendant.getAttendantCode()))
			{
				stm.setString(1, Attendant.getAttendantCode());
				stm.setString(2, Attendant.getAttendantCode());
				stm.setNull(3, java.sql.Types.NULL);
				stm.setNull(4, java.sql.Types.NULL);
				stm.setString(5, Attendant.getAttendantPassword());
			}
			else if(!("").equals(Attendant.getAttendantEmail()))
			{
				stm.setNull(1, java.sql.Types.NULL);
				stm.setNull(2, java.sql.Types.NULL);
				stm.setString(3, Attendant.getAttendantEmail());
				stm.setString(4, Attendant.getAttendantEmail());
				stm.setString(5, Attendant.getAttendantPassword());
				
			}
			
			rs  = stm.executeQuery();
			System.out.println(stm);
			Attendant stud = new Attendant();
			List<Attendant> Attendantlist = new ArrayList<Attendant>();
			if (rs.next())
			{
				stud.setAttendantId(rs.getInt("attendant_id"));
				stud.setAttendantCode(rs.getString("attendant_code"));
				stud.setAttendantEmail(rs.getString("attendant_email"));
				stud.setAttendantPassword(rs.getString("attendant_password"));
				stud.setAttendantFirstName(rs.getString("attendant_first_name"));
				stud.setAttendantLastName(rs.getString("attendant_last_name"));
				stud.setPic(rs.getString("pic"));
				message.setStatusCode("0");
				message.setStatusMsg("login complete");
				Attendantlist.add(stud);
				System.err.println(rs.getString("attendant_password"));
				message.setList(Attendantlist);
			}
			else
			{
				message.setStatusCode("1");
				message.setStatusMsg("login incorrect");
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

	
	public Message<Attendant> getAttendant() throws Exception {
		
		System.out.println("on method getAttendant() of Attendant table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Attendant> message = new Message<Attendant>();
		try {		
			String sql = "select *  "+
				" from attendant  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			rs  = stm.executeQuery();
			Attendant stud = null;
			List<Attendant> Attendantlist = new ArrayList<Attendant>();
			while (rs.next())
			{
				stud = new Attendant();
				stud.setAttendantCode(rs.getString("attendant_code"));
				stud.setAttendantFirstName(rs.getString("attendant_first_name"));
				stud.setAttendantLastName(rs.getString("attendant_last_name"));
				
				message.setStatusCode("0");
				message.setStatusMsg("attendant found");
				Attendantlist.add(stud);
				message.setList(Attendantlist);
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
	
	public Message<Student> getAttendantDashBoard(String attendantCode) throws Exception {
		
		System.out.println("on method getStudentDashBoard() of apprentice , student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Student> message = new Message<Student>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
		try {		
			/*String sql = "select s.*  from apprentice a join student s "+
				" on a.student_code = s.student_code where a.student_code = ? and a.attendant_code = ? ";*/
			String sql = "select s.* , count(*) as amount  "
					+ "from diagnosis d join student s "+
				" on d.student_code = s.student_code where d.attendant_code = ?  and d.approve = 'N' "
				+ " group by student_first_name ,student_last_name ,"
				+ "student_code ,student_email ,student_password ,"
				+ "student_university ,apprentice_startdate ,"
				+ "apprentice_enddate ,attendant_code , pic ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, attendantCode);
			rs  = stm.executeQuery();
			Student stud = null;
			List<Student> Studentlist = new ArrayList<Student>();
			while (rs.next())
			{
				stud = new Student();
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				stud.setStudentEmail(rs.getString("student_email"));
				stud.setStudentUniversity(rs.getString("student_university"));
				String parsedStart = format.format(rs.getDate("apprentice_startdate"));
				String parsedEnd= format.format(rs.getDate("apprentice_enddate"));
				stud.setApprenticeStartDate(parsedStart);
				stud.setApprenticeEndDate(parsedEnd);
				stud.setAmount(rs.getString("amount"));
				stud.setPic(rs.getString("pic"));
				message.setStatusCode("0");
				message.setStatusMsg("student found");
				Studentlist.add(stud);
				message.setList(Studentlist);
			}
			
			
			
			if(Studentlist == null || Studentlist.size() == 0) 
			{
				message.setStatusCode("1");
				message.setStatusMsg("no student for this attendant");
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
	
	
public Message<DiagnosisResult> getSummaryReport(String attendantCode, String universityCode,String startDate ,String endDate) throws Exception {
		
		System.out.println("on method getSummaryReport() ");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<DiagnosisResult> message = new Message<DiagnosisResult>();
		
		try {	
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			String where = " where 1 = 1 and s.student_university = '"+universityCode+"' and d.attendant_code = '"+attendantCode+"' ";
			if (startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("") ) {
				
				Date dateStartFormat = formatter.parse(startDate);
				java.sql.Date startDateFormat = new java.sql.Date(dateStartFormat.getTime());
				
				Date dateEndFormat = formatter.parse(endDate);
				java.sql.Date endDateFormat = new java.sql.Date(dateEndFormat.getTime());
				
				
			   where +=  " and  DATE(diagnosis_datetime) between '" + startDateFormat+"' and '"+endDateFormat+"'";
			}
			
			String SQL = "select *  "
			           + "  from diagnosis d join student s "
			           + " on s.student_code = d.student_code "
			           + " join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id " 
			           +   where;
			
			System.out.println(SQL);
			
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(SQL);
		
			
			rs  = stm.executeQuery();
			System.out.println(stm);
			DiagnosisResult stud = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<DiagnosisResult> diagnosisResult = new ArrayList<DiagnosisResult>();
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
				
				//String diagnosisDatetime = format.format(rs.getDate("diagnosis_datetime"));
				stud.setDiagnosisDateTime(rs.getTimestamp("diagnosis_datetime"));
				stud.setCreateDate(rs.getTimestamp("diagnosis_datetime"));
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
				stud.setApprove(rs.getString("approve"));
				stud.setApproveDate(rs.getTimestamp("approve_date"));
				stud.setConfidenceDetail(rs.getString("confidence_detail"));
				
		
				message.setStatusCode("0");
				message.setStatusMsg("getSummaryReport found");
				diagnosisResult.add(stud);
				message.setList(diagnosisResult);
			}
			
			
			if(diagnosisResult == null || diagnosisResult.size() == 0) 
			{
				message.setStatusCode("1");
				message.setStatusMsg("Knowledge now row");
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


public int amountStudentForOverall( String universityCode,String attendantCode) throws Exception {
	
	System.out.println("on method amountStudentForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  , d.student_code "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ? ";
		       	if(!attendantCode.equals("A") && !attendantCode.equals(""))
					SQL += " and d.attendant_code = "+attendantCode+" ";
		SQL += " group by d.student_code " ;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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
	
public int amountStudentForOverall(String universityCode) throws Exception {
	
	System.out.println("on method amountStudentForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from   student s "
		           + " where  s.student_university = ? " ;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountStudentForOverall(String universityCode,String attendantCode , String startDate , String endDate ) throws Exception {
	
	System.out.println("on method amountStudentForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
	
		
		String SQL = "select count(*) amount , d.student_code  "
		           + "  from   student s join diagnosis d on d.student_code = s.student_code "
		           + " where  s.student_university =  ? " ;
		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		if((!startDate.equals("") && startDate != null) && (!endDate.equals("") && endDate != null))
		{
			Date dateStartFormat = formatter.parse(startDate);
			java.sql.Date startDateFormat = new java.sql.Date(dateStartFormat.getTime());
			
			Date dateEndFormat = formatter.parse(endDate);
			java.sql.Date endDateFormat = new java.sql.Date(dateEndFormat.getTime());
			SQL += " and Date(d.diagnosis_datetime) between  '"+startDateFormat+"'  and '"+endDateFormat+"' ";
		}
			
		
		SQL += " group by d.student_code";
		System.out.println(SQL);
		
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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


public int amountHandInForOverall( String universityCode,String attendantCode) throws Exception {
	
	System.out.println("on method amountHandInForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?   " ;
		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountHandInForOverall(String universityCode) throws Exception {
	
	System.out.println("on method amountHandInForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?  " ;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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



public int amountIsReadForOverall( String universityCode,String attendantCode) throws Exception {
	
	System.out.println("on method amountIsReadForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?  and d.isRead = 'Y'  " ;
		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountIsReadForOverall(String universityCode) throws Exception {
	
	System.out.println("on method amountIsReadForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?  and d.isRead = 'Y' " ;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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



public int amountDianosisForOverall(String universityCode,String attendantCode ) throws Exception {
	
	System.out.println("on method amountDianosisForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?    " ;
		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountDianosisForOverall(String universityCode) throws Exception {
	
	System.out.println("on method amountDianosisForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?   " ;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountDianosisForOverall(String universityCode,String attendantCode ,String startDate ,String endDate) throws Exception {
	
	System.out.println("on method amountDianosisForOverall() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
	
		
		String SQL = "select count(*) amount  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ?   " ;
		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		if((!startDate.equals("") && startDate != null) && (!endDate.equals("") && endDate != null))
		{
			Date dateStartFormat = formatter.parse(startDate);
			java.sql.Date startDateFormat = new java.sql.Date(dateStartFormat.getTime());
			
			Date dateEndFormat = formatter.parse(endDate);
			java.sql.Date endDateFormat = new java.sql.Date(dateEndFormat.getTime());
			SQL += " and Date(d.diagnosis_datetime) between  '"+startDateFormat+"'  and '"+endDateFormat+"' ";
		}
			
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public int amountConfidenceScore(String universityCode, int score , String attendantCode , String startDate ,String endDate) throws Exception {
	
	System.out.println("on method amountConfidenceScore() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	int amount = 0 ;
	
	try {	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
	
		String SQL = "select count(*) amount  , d.confidence_score  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " where  s.student_university = ? and d.confidence_score = ?  " ;

		if(!attendantCode.equals("A") && !attendantCode.equals(""))
			SQL += " and d.attendant_code = "+attendantCode+" ";
		if((!startDate.equals("") && startDate != null) && (!endDate.equals("") && endDate != null))
		{
			Date dateStartFormat = formatter.parse(startDate);
			java.sql.Date startDateFormat = new java.sql.Date(dateStartFormat.getTime());
			
			Date dateEndFormat = formatter.parse(endDate);
			java.sql.Date endDateFormat = new java.sql.Date(dateEndFormat.getTime());
			SQL += " and Date(d.diagnosis_datetime) between  '"+startDateFormat+"'  and '"+endDateFormat+"' ";
		}
		
		SQL += " group by d.confidence_score ";
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
		//stm.setString(1, attendantCode);
		stm.setString(1, universityCode);
		stm.setInt(2, score);
		
		rs  = stm.executeQuery();
		System.out.println(stm);
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

public Message<DiagnosisResult> getDelayReport(String attendantCode , String approve) throws Exception {
	
	System.out.println("on method getDelayReport() ");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	
	try {	
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String where = " where 1 = 1  and d.attendant_code = '"+attendantCode+"' and approve = '"+approve+"' and status = 'D' ";
		String SQL = "select *  "
		           + "  from diagnosis d join student s "
		           + " on s.student_code = d.student_code "
		           + " join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id " 
		           +   where;
		
		System.out.println(SQL);
		
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(SQL);
	
		
		rs  = stm.executeQuery();
		System.out.println(stm);
		DiagnosisResult stud = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<DiagnosisResult> diagnosisResult = new ArrayList<DiagnosisResult>();
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
			
			//String diagnosisDatetime = format.format(rs.getDate("diagnosis_datetime"));
			stud.setDiagnosisDateTime(rs.getTimestamp("diagnosis_datetime"));
			stud.setCreateDate(rs.getTimestamp("diagnosis_datetime"));
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
			stud.setApprove(rs.getString("approve"));
			stud.setApproveDate(rs.getTimestamp("approve_date"));
			stud.setConfidenceDetail(rs.getString("confidence_detail"));
			stud.setIsRead(rs.getString("isRead"));
	
			message.setStatusCode("0");
			message.setStatusMsg("getDelayReport found");
			diagnosisResult.add(stud);
			message.setList(diagnosisResult);
		}
		
		
		if(diagnosisResult == null || diagnosisResult.size() == 0) 
		{
			message.setStatusCode("1");
			message.setStatusMsg("getDelayReport now row");
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
/*	
public Message<Student> getStudentDashBoard(String studentCode) throws Exception {
		
		System.out.println("on method getStudentDashBoard() of apprentice , student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Student> message = new Message<Student>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
		try {		
			String sql = "select s.*  from apprentice a join student s "+
				" on a.student_code = s.student_code where a.student_code = ?  ";
			String sql = "select s.* , count(*) as amount  "
					+ "from diagnosis d join student s "+
				" on d.student_code = s.student_code where s.student_code = ? "
				+ " group by student_first_name ,student_last_name ,"
				+ "student_code ,student_email ,student_password ,"
				+ "student_university ,apprentice_startdate ,"
				+ "apprentice_enddate ,attendant_code ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, studentCode);
			rs  = stm.executeQuery();
			Student stud = null;
			List<Student> Studentlist = new ArrayList<Student>();
			while (rs.next())
			{
				stud = new Student();
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				stud.setStudentEmail(rs.getString("student_email"));
				stud.setStudentUniversity(rs.getString("student_university"));
				String parsedStart = format.format(rs.getDate("apprentice_startdate"));
				String parsedEnd= format.format(rs.getDate("apprentice_enddate"));
				stud.setApprenticeStartDate(parsedStart);
				stud.setApprenticeEndDate(parsedEnd);
				stud.setAmount(rs.getString("amount"));
				message.setStatusCode("0");
				message.setStatusMsg("student found");
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

public Message<Student> getStudentDashBoardAll() throws Exception {
	
	System.out.println("on method getStudentDashBoardAll() of apprentice , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Student> message = new Message<Student>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
	try {		
		String sql = "select s.* , count(*) as amount  "
				+ "from diagnosis d join student s "+
			" on d.student_code = s.student_code "
			+ " group by student_first_name ,student_last_name ,"
			+ "student_code ,student_email ,student_password ,"
			+ "student_university ,apprentice_startdate ,"
			+ "apprentice_enddate ,attendant_code ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		//stm.setString(1, attendantCode);
		rs  = stm.executeQuery();
		Student stud = null;
		List<Student> Studentlist = new ArrayList<Student>();
		while (rs.next())
		{
			stud = new Student();
			stud.setStudentCode(rs.getString("student_code"));
			stud.setStudentFirstName(rs.getString("student_first_name"));
			stud.setStudentLastName(rs.getString("student_last_name"));
			stud.setStudentEmail(rs.getString("student_email"));
			stud.setStudentUniversity(rs.getString("student_university"));
			String parsedStart = format.format(rs.getDate("apprentice_startdate"));
			String parsedEnd= format.format(rs.getDate("apprentice_enddate"));
			stud.setApprenticeStartDate(parsedStart);
			stud.setApprenticeEndDate(parsedEnd);
			stud.setAmount(rs.getString("amount"));
			message.setStatusCode("0");
			message.setStatusMsg("student found");
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
	public static void main(String[] args) {
	/*	Attendant stu = new Attendant();
		stu.setAttendantCode("590001");
		stu.setAttendantEmail("tongmaster@gmail.com");
		stu.setAttendantPassword("44pps");*/
	/*	AttendantDao methodStud = new AttendantDao();
		try {
			methodStud.getStudentDashBoard("182736", "9002345");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}*/
	}
}
