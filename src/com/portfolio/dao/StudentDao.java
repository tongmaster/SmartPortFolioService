package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portfolio.model.DiagnosisResult;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class StudentDao {
	public Message<Student> insertStudent(Student student) throws Exception {
		
		System.out.println("on method insertStudent() of Student table");
		Connection conn = null;
		Message<Student> message = new Message<Student>();
		try {
			/*String sqlInsert = "insert into student (student_code, student_email, student_password , "
					+ " student_first_name, student_last_name, student_university,apprentice_startdate ,apprentice_enddate,attendant_code) "
					+ " values (?,?,?,?,?,?,?,?,?)";*/
			String sqlInsert = "insert into student (student_code, student_email, student_password , "
					+ " student_first_name, student_last_name, student_university,apprentice_startdate ,apprentice_enddate,pic) "
					+ " values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStartFormat = formatter.parse(student.getApprenticeStartDate());
			Date dateEndFormat = formatter.parse(student.getApprenticeEndDate());
			java.sql.Date startDate = new java.sql.Date(dateStartFormat.getTime());
			java.sql.Date endDate = new java.sql.Date(dateEndFormat.getTime());
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, student.getStudentCode());
			insert.setString(2, student.getStudentEmail());
			insert.setString(3, student.getStudentPassword());
			insert.setString(4, student.getStudentFirstName());
			insert.setString(5, student.getStudentLastName());
			insert.setString(6, student.getStudentUniversity());
			insert.setDate(7, startDate);
			insert.setDate(8, endDate);
			insert.setString(9, student.getPic());
			//insert.setString(9,student.getAttendantCode());
	
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertStudent Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertStudent Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertStudent Complete  =====================");

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
public static void main(String[] args) throws ParseException {
	
	Student student = new Student();
	student.setApprenticeStartDate("2016-01-10");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date dateEndFormat = formatter.parse(student.getApprenticeStartDate());
	java.sql.Timestamp startDate = new java.sql.Timestamp(dateEndFormat.getTime());
	System.out.println(startDate);
}
	
public Message<Student> checkLogin(Student student) throws Exception {
		
		System.out.println("on method checkLogin() of Student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Student> message = new Message<Student>();
		try {		
			String sql = "select *  "+
				" from student  where (? is null OR (student_code = ?)) AND (? is null OR (student_email = ?)) and student_password = ?  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			if(!("").equals(student.getStudentCode()))
			{
				stm.setString(1, student.getStudentCode());
				stm.setString(2, student.getStudentCode());
				stm.setNull(3, java.sql.Types.NULL);
				stm.setNull(4, java.sql.Types.NULL);
				stm.setString(5, student.getStudentPassword());
			}
			else if(!("").equals(student.getStudentEmail()))
			{
				stm.setNull(1, java.sql.Types.NULL);
				stm.setNull(2, java.sql.Types.NULL);
				stm.setString(3, student.getStudentEmail());
				stm.setString(4, student.getStudentEmail());
				stm.setString(5, student.getStudentPassword());
				
			}
			
			rs  = stm.executeQuery();
			Student stud = new Student();
			List<Student> studentlist = new ArrayList<Student>();
			if (rs.next())
			{
				stud.setStudentId(rs.getInt("student_id"));
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentEmail(rs.getString("student_email"));
				stud.setStudentPassword(rs.getString("student_password"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				stud.setPic(rs.getString("pic"));
				message.setStatusCode("0");
				message.setStatusMsg("login complete");
				studentlist.add(stud);
				System.err.println(rs.getString("student_password"));
				message.setList(studentlist);
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

public Student getStudent(String studentCode) throws Exception {
	
	System.out.println("on method getStudent() of Student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Student stud = new Student();
	try {		
		String sql = "select *  "+
			" from student  where student_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		
		stm.setString(1, studentCode);
		
		
		rs  = stm.executeQuery();
		
		if (rs.next())
		{
			stud.setStudentId(rs.getInt("student_id"));
			stud.setStudentCode(rs.getString("student_code"));
			stud.setStudentEmail(rs.getString("student_email"));
			stud.setStudentPassword(rs.getString("student_password"));
			stud.setStudentFirstName(rs.getString("student_first_name"));
			stud.setStudentLastName(rs.getString("student_last_name"));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String start = format.format(rs.getDate("apprentice_startdate"));
			String end = format.format(rs.getDate("apprentice_enddate"));
			stud.setApprenticeStartDate(start);
			stud.setApprenticeEndDate(end);
			
			System.err.println(rs.getString("student_password"));
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
	return stud;
}


public String getStudentDashBoard(String studendCode, String status , String approve) throws Exception {
	
	System.out.println("on method getStudentDashBoard()"+studendCode);
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Student> message = new Message<Student>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	String num = "0";
    
	try {		
		/*String sql = "select s.*  from apprentice a join student s "+
			" on a.student_code = s.student_code where a.student_code = ? and a.attendant_code = ? ";*/
		String sql = "select   count(*) as amount  "
				+ "from diagnosis "
			+ " where student_code = ? ";
		if(status.equals("Y") ||  status.equals("N") )
			sql += " and status ='"+status+"'";

		if(approve.equals("Y"))
			sql += " and approve ='"+approve+"' and isRead = 'N'  ";
		
		if(approve.equals("N"))
			sql += " and approve ='"+approve+"' ";
		
		sql +="  order by create_date desc ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, studendCode);
		rs  = stm.executeQuery();
		if (rs.next())
		{
			System.out.println(rs.getString("amount"));
			num = (rs.getString("amount"));
			
		
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
	return num;
}

public Message<DiagnosisResult> inspectlist(String studentCode,String digonosisId,String status,String approve) throws Exception {
	
	System.out.println("on method inspectlist() of diagonosis , student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<DiagnosisResult> message = new Message<DiagnosisResult>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
	try {		
		String sql = "select d.*,s.student_first_name, s.student_last_name ,a.attendant_first_name,a.attendant_last_name, mp.md_pro_name"
				+ "   from  diagnosis d join student s "+
			" on d.student_code = s.student_code  join attendant a "
			+ " on a.attendant_code = d.attendant_code "
			+ " join medical_procedure  mp  on mp.md_pro_id = d.md_pro_id "
			+ "where  d.student_code = '"+studentCode+"' ";
		
		if(!digonosisId.equals(""))
			sql += " and  d.diagnosis_id = "+Integer.parseInt(digonosisId)+" ";
		if(!status.equals(""))
			sql += " and  d.status = '"+status+"' ";
		if(!approve.equals(""))
			sql += " and  d.approve = '"+approve+"' ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
	
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
			stud.setApproveDate(rs.getTimestamp("approve_date"));
			stud.setIsRead(rs.getString("isRead"));
			
			stud.setAttendantFirstName(rs.getString("attendant_first_name"));
			stud.setAttendantLastName(rs.getString("attendant_last_name"));
			message.setStatusCode("0");
			message.setStatusMsg("inspectlist found");
			Studentlist.add(stud);
			message.setList(Studentlist);
		}
		
		if(Studentlist == null || Studentlist.size() == 0) 
		{
			message.setStatusCode("1");
			message.setStatusMsg("no inspectlist for this student");
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
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setStudentCode("590001");
		stu.setStudentEmail("tongmaster@gmail.com");
		stu.setStudentPassword("44pps");
		StudentDao methodStud = new StudentDao();
		try {
			methodStud.insertStudent(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}
	}*/
}
