package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class KnowledgeDao {

	
public Message<Knowledge> findKnowledge(Knowledge knowldge) throws Exception {
		
		System.out.println("on method findKnowledge() of Knowledge table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Knowledge> message = new Message<Knowledge>();
		
		try {		
			String sql = "select *  "+
				" from Knowledge  where (knowledge_name = ? or knowledge_id = ? or knowledge_createdate = ? ) ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStartFormat = formatter.parse(knowldge.getKnowledgeCreatedate());
			java.sql.Date createDate = new java.sql.Date(dateStartFormat.getTime());
			stm.setString(1, knowldge.getKnowledgeCreateName());
			stm.setString(2, knowldge.getKnowledgeId());
			stm.setDate(3, createDate);
			rs  = stm.executeQuery();
			Knowledge stud = new Knowledge();
			List<Knowledge> Knowledgelist = new ArrayList<Knowledge>();
			if (rs.next())
			{
				stud.setKnowledgeCreateName(rs.getString("knowledge_name"));
				stud.setKnowledgeId(rs.getString("knowledge_id"));
				stud.setKnowledgeCatId(rs.getString("knowledge_cat_id"));
				String date_to_string = formatter.format(rs.getDate("knowledge_createdate"));
				stud.setKnowledgeCreatedate(date_to_string);
				stud.setKnowledgePath(rs.getString("knowledge_path"));
				stud.setKnowledgeCreateName(rs.getString("knowledge_create_name"));
				stud.setKnowledgeDesc(rs.getString("knowledge_desc"));
				message.setStatusCode("0");
				message.setStatusMsg("Knowledge found");
				Knowledgelist.add(stud);
				System.err.println(rs.getString("student_password"));
				message.setList(Knowledgelist);
			}
			else
			{
				message.setStatusCode("1");
				message.setStatusMsg("Knowledge not found");
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



	
}
