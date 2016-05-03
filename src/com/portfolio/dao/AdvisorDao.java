package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Message;
import com.portfolio.model.Advisor;
import com.portfolio.util.ConnectionHelper;



public class AdvisorDao {
	public Message<Advisor> insertAdvisor(Advisor Advisor) throws Exception {
		
		System.out.println("on method insertAdvisor() of Advisor table");
		Connection conn = null;
		Message<Advisor> message = new Message<Advisor>();
		try {
			String sqlInsert = "insert into advisor (advisor_code, advisor_email, advisor_password , advisor_first_name, advisor_last_name , advisor_university) "
					+ " values (?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, Advisor.getAdvisorCode());
			insert.setString(2, Advisor.getAdvisorEmail());
			insert.setString(3, Advisor.getAdvisorPassword());
			insert.setString(4, Advisor.getAdvisorFirstName());
			insert.setString(5, Advisor.getAdvisorLastName());
			insert.setString(6, Advisor.getAdvisorUniversity());
	
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertAdvisor Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertAdvisor Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertAdvisor Complete  =====================");

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

	
public Message<Advisor> checkLogin(Advisor Advisor) throws Exception {
		
		System.out.println("on method checkLogin() of Advisor table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Advisor> message = new Message<Advisor>();
		try {		
			String sql = "select *  "+
				" from Advisor  where (advisor_code = ? or advisor_email = ?) and advisor_password = ?  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, Advisor.getAdvisorCode());
			stm.setString(2, Advisor.getAdvisorEmail());
			stm.setString(3, Advisor.getAdvisorPassword());
			rs  = stm.executeQuery();
			Advisor stud = new Advisor();
			List<Advisor> Advisorlist = new ArrayList<Advisor>();
			if (rs.next())
			{
				stud.setAdvisorId(rs.getInt("advisor_id"));
				stud.setAdvisorCode(rs.getString("advisor_code"));
				stud.setAdvisorEmail(rs.getString("advisor_email"));
				stud.setAdvisorPassword(rs.getString("advisor_password"));
				stud.setAdvisorFirstName(rs.getString("advisor_first_name"));
				stud.setAdvisorLastName(rs.getString("advisor_last_name"));
				message.setStatusCode("0");
				message.setStatusMsg("login complete");
				Advisorlist.add(stud);
				System.err.println(rs.getString("advisor_password"));
				message.setList(Advisorlist);
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



	public static void main(String[] args) {
		Advisor stu = new Advisor();
		stu.setAdvisorCode("590001");
		stu.setAdvisorEmail("tongmaster@gmail.com");
		stu.setAdvisorPassword("44pps");
		AdvisorDao methodStud = new AdvisorDao();
		try {
			methodStud.insertAdvisor(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}
	}
}
