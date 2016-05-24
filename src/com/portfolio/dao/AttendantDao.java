package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Message;
import com.portfolio.model.Attendant;
import com.portfolio.model.MedicalProcedure;
import com.portfolio.util.ConnectionHelper;



public class AttendantDao {
	public Message<Attendant> insertAttendant(Attendant Attendant) throws Exception {
		
		System.out.println("on method insertAttendant() of Attendant table");
		Connection conn = null;
		Message<Attendant> message = new Message<Attendant>();
		try {
			String sqlInsert = "insert into attendant (attendant_code, attendant_email, attendant_password , attendant_first_name, attendant_last_name , attendant_university) "
					+ " values (?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, Attendant.getAttendantCode());
			insert.setString(2, Attendant.getAttendantEmail());
			insert.setString(3, Attendant.getAttendantPassword());
			insert.setString(4, Attendant.getAttendantFirstName());
			insert.setString(5, Attendant.getAttendantLastName());
			insert.setString(6, Attendant.getAttendantUniversity());
	
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


	public static void main(String[] args) {
		Attendant stu = new Attendant();
		stu.setAttendantCode("590001");
		stu.setAttendantEmail("tongmaster@gmail.com");
		stu.setAttendantPassword("44pps");
		AttendantDao methodStud = new AttendantDao();
		try {
			methodStud.insertAttendant(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}
	}
}
