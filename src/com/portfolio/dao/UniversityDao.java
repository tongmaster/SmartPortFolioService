package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Attendant;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.model.University;
import com.portfolio.util.ConnectionHelper;



public class UniversityDao {
	

	
public Message<University> findUniversity() throws Exception {
		
		System.out.println("on method findUniversity() of University table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<University> message = new Message<University>();
		try {		
			String sql = "select *  "+
				" from university  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			rs  = stm.executeQuery();
			University stud = null;
			List<University> Advisorlist = new ArrayList<University>();
			while (rs.next())
			{
				stud = new University();
				stud.setUniversityCode(rs.getString("university_code"));
				stud.setUniversityName(rs.getString("university_name"));
				
				message.setStatusCode("0");
				message.setStatusMsg("university found");
				Advisorlist.add(stud);
				message.setList(Advisorlist);
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

public Message<University> findUniversity(String universityCode) throws Exception {
	
	System.out.println("on method findUniversity() of University table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<University> message = new Message<University>();
	try {		
		String sql = "select *  "+
			" from university where university_code = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, universityCode);
		rs  = stm.executeQuery();
		University stud = null;
		List<University> Advisorlist = new ArrayList<University>();
		while (rs.next())
		{
			stud = new University();
			stud.setUniversityCode(rs.getString("university_code"));
			stud.setUniversityName(rs.getString("university_name"));
			
			message.setStatusCode("0");
			message.setStatusMsg("university found");
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("university not  found");
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

public Message<Attendant> findUniversityFromAttendant(String universityCode) throws Exception {
	
	System.out.println("on method findUniversityFromAttendant() of University table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Attendant> message = new Message<Attendant>();
	try {		
		String sql = "select *  "+
			" from attendant where attendant_university = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, universityCode);
		rs  = stm.executeQuery();
		Attendant stud = null;
		List<Attendant> Advisorlist = new ArrayList<Attendant>();
		while (rs.next())
		{
			stud = new Attendant();
			stud.setAttendantUniversity(rs.getString("attendant_university"));
			message.setStatusCode("0");
			message.setStatusMsg("Attendant of university found");
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("Attendant of university not  found");
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


public Message<Student> findUniversityFromStudent(String universityCode) throws Exception {
	
	System.out.println("on method findUniversityFromStudent() of Student table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Student> message = new Message<Student>();
	try {		
		String sql = "select *  "+
			" from student where student_university = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, universityCode);
		rs  = stm.executeQuery();
		Student stud = null;
		List<Student> Advisorlist = new ArrayList<Student>();
		while (rs.next())
		{
			stud = new Student();
			stud.setStudentUniversity(rs.getString("student_university"));
			message.setStatusCode("0");
			message.setStatusMsg("Student of university found");
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("Student of university not  found");
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


public Message<University> insertUniversity(University university) throws Exception {
	
	System.out.println("on method insertUniversity() of University table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<University> message = new Message<University>();
	try {		
		String sql = "insert into university (university_code , university_name )  "+
			" values (?,?)";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, university.getUniversityCode());
		stm.setString(2, university.getUniversityName());
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("insertUniversity Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("insertUniversity Completed");
			message.setStatusCode("0");
			System.out.println("=================== End insertUniversity Complete  =====================");

		}
		
		
		
			//System.out.println("haveRow>>> false");
		conn.close();
		stm.close();
		

	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return message;
}


public Message<University> updateUniversity(University university) throws Exception {
	
	System.out.println("on method updateUniversity() of University table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<University> message = new Message<University>();
	try {		
		String sql = "update university set university_name = ?   "+
			"  where university_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, university.getUniversityName());
		stm.setString(2, university.getUniversityCode());
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("updateUniversity Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("updateUniversity Completed");
			message.setStatusCode("0");
			System.out.println("=================== End updateUniversity Complete  =====================");

		}
		
		conn.close();
		stm.close();
		

	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return message;
}

public Message<University> deleteUniversity(String universityCode) throws Exception {
	
	System.out.println("on method deleteUniversity() of University table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<University> message = new Message<University>();
	try {		
		String sql = "delete from  university   "+
			"  where university_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, universityCode);
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("deleteUniversity Completed");
			message.setStatusMsg("0");
		} else {
			message.setStatusMsg("deleteUniversity Incorrect");
			message.setStatusCode("1");
			System.out.println("=================== End deleteUniversity Complete  =====================");

		}
		
		conn.close();
		stm.close();
		

	} catch (Exception e) {
		e.printStackTrace();
		String tableKey = e.getMessage();
		throw new Exception(
				"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
	}
	conn.close();
	return message;
}


/*	public static void main(String[] args) {
		Advisor stu = new Advisor();
		stu.setAdvisorCode("590001");
		stu.setAdvisorEmail("tongmaster@gmail.com");
		stu.setAdvisorPassword("44pps");
		UniversityDao methodStud = new UniversityDao();
		try {
			methodStud.insertAdvisor(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}
	}*/
}
