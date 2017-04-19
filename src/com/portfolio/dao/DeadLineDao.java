package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.portfolio.model.Deadline;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;

public class DeadLineDao {
	


public Deadline findDeadLine(String attendentCode ) throws Exception {
	
	System.out.println("on method findDeadLine() of deadline table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Deadline stud = null;
	Message<Deadline> message = new Message<Deadline>();
	try {		
		String sql = "select *  "+
			" from deadline  where  attendant_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, attendentCode);
		rs  = stm.executeQuery();
		System.out.println(stm);
		if (rs.next())
		{
			stud = new Deadline();
		
			stud.setAttendentCode(rs.getString("attendant_code"));
			stud.setDeadline1(rs.getString("deadline1"));
			stud.setDeadline2(rs.getString("deadline2"));
			System.out.println(stud.getDeadline1());
			
		
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

public Message<Deadline> insertDeadLine(Deadline deadline) throws Exception {
	
	System.out.println("on method insertDeadLine() of deadline table");
	Connection conn = null;
	Message<Deadline> message = new Message<Deadline>();
	try {
		
		String sqlInsert = "insert into deadline (attendant_code, deadline1 , create_date )"
				+ " values (?,?,now())";
		PreparedStatement insert;
		conn = ConnectionHelper.getConnection();
		/*
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStartFormat = formatter.parse(student.getApprenticeStartDate());
		Date dateEndFormat = formatter.parse(student.getApprenticeEndDate());
		java.sql.Date startDate = new java.sql.Date(dateStartFormat.getTime());
		java.sql.Date endDate = new java.sql.Date(dateEndFormat.getTime());
		*/
		insert = conn.prepareStatement(sqlInsert);
		insert.setString(1, deadline.getAttendentCode());
		insert.setString(2, deadline.getDeadline1());
	
		//insert.setString(9,student.getAttendantCode());

		insert.executeUpdate();

		int rows = insert.getUpdateCount();
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("insertDeadLine and Attendant Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("insertDeadLine and Attendant Completed");
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

public static void main(String[] args) {
	DeadLineDao a = new DeadLineDao();
	try {
		Deadline b =  a.findDeadLine( "9002345");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
