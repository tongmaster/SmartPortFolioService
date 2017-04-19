package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Admin;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class AdminDao {


	
public Message<Admin> checkLogin(Admin admin) throws Exception {
		
		System.out.println("on method checkLogin() of Admin table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Admin> message = new Message<Admin>();
		try {		
			String sql = "select *  "+
				" from admin  where (? is null OR (username = ?)) AND (? is null OR (email = ?)) and password = ?  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			if(!("").equals(admin.getUsername()))
			{
				stm.setString(1, admin.getUsername());
				stm.setString(2, admin.getUsername());
				stm.setNull(3, java.sql.Types.NULL);
				stm.setNull(4, java.sql.Types.NULL);
				stm.setString(5, admin.getPassword());
			}
			else if(!("").equals(admin.getEmail()))
			{
				stm.setNull(1, java.sql.Types.NULL);
				stm.setNull(2, java.sql.Types.NULL);
				stm.setString(3, admin.getEmail());
				stm.setString(4, admin.getEmail());
				stm.setString(5, admin.getPassword());
				
			}
			
			rs  = stm.executeQuery();
			Admin stud = new Admin();
			List<Admin> studentlist = new ArrayList<Admin>();
			if (rs.next())
			{
				stud.setUsername(rs.getString("username"));
				stud.setPassword(rs.getString("password"));
				stud.setEmail(rs.getString("email"));
				stud.setCreateDate(rs.getTimestamp("create_date"));
				stud.setStatus(rs.getString("status"));
				stud.setMember(rs.getString("member"));
				stud.setUniversity(rs.getString("university"));
				stud.setDignosis(rs.getString("dignosis"));
				stud.setKnowlege(rs.getString("knowlege"));
				stud.setMedicine(rs.getString("medicine"));
				message.setStatusCode("0");
				message.setStatusMsg("login complete");
				studentlist.add(stud);
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

}
