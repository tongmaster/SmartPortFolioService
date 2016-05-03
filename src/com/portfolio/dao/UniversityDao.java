package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Message;
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
