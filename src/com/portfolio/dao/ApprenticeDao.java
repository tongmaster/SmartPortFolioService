package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portfolio.model.Apprentice;
import com.portfolio.model.Message;
import com.portfolio.model.University;
import com.portfolio.util.ConnectionHelper;
import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.App;

public class ApprenticeDao {
	
public Message<Apprentice> insertApprentice(Apprentice apprentice) throws Exception {
		
		System.out.println("on method insertApprentice() of apprentice table");
		Connection conn = null;
		Message<Apprentice> message = new Message<Apprentice>();
		try {
			String sqlInsert = "insert into apprentice (student_code, apprentice_startdate ,apprentice_enddate,attendant_code ,create_date) "
					+ " values (?,?,?,?,now())";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStartFormat = formatter.parse(apprentice.getApprenticeStartDate());
			Date dateEndFormat = formatter.parse(apprentice.getApprenticeEndDate());
			java.sql.Date startDate = new java.sql.Date(dateStartFormat.getTime());
			java.sql.Date endDate = new java.sql.Date(dateEndFormat.getTime());
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, apprentice.getStudentCode());
			insert.setDate(2, startDate);
			insert.setDate(3, endDate);
			insert.setString(4,apprentice.getAttendantCode());
	
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertApprentice Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertApprentice Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertApprentice Complete  =====================");

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

public Apprentice findApprentice(String attendantCode , String studentCode) throws Exception {
	
	System.out.println("on method findApprentice() of Apprentice table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Apprentice stud = null;
	Message<Apprentice> message = new Message<Apprentice>();
	try {		
		String sql = "select *  "+
			" from apprentice  where student_code = ? and attendant_code = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, studentCode);
		stm.setString(2, attendantCode);
		rs  = stm.executeQuery();
		System.out.println(stm);
		if (rs.next())
		{
			stud = new Apprentice();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        String parsed = format.format(rs.getDate("apprentice_startdate"));
	        System.out.println(parsed);
			stud.setApprenticeStartDate(parsed);
			String parsed2 = format.format(rs.getDate("apprentice_enddate"));
			stud.setApprenticeEndDate(parsed2);
			stud.setStudentCode(rs.getString("student_code"));
			stud.setAttendantCode(rs.getString("attendant_code"));
			
		
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

public static void main(String[] args) {
	ApprenticeDao a = new ApprenticeDao();
	try {
		Apprentice b =  a.findApprentice( "9002345","182736");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
