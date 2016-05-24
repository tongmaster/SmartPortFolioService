package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class KnowledgeDao {

	
public Message<Knowledge> findKnowledge(Knowledge knowldge,String startDate ,String endDate) throws Exception {
		
		System.out.println("on method findKnowledge() of Knowledge table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Knowledge> message = new Message<Knowledge>();
		
		try {	
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			String where = " where 1 = 1 ";
			if (startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("") ) {
				
				Date dateStartFormat = formatter.parse(startDate);
				java.sql.Date startDateFormat = new java.sql.Date(dateStartFormat.getTime());
				
				Date dateEndFormat = formatter.parse(endDate);
				java.sql.Date endDateFormat = new java.sql.Date(dateEndFormat.getTime());
				
				
			   where +=  " and  knowledge_createdate between '" + startDateFormat+"' and '"+endDateFormat+"'";
			}
			if (knowldge.getKnowledgeCatId() != null && !"".equals(knowldge.getKnowledgeCatId())) {
			   where +=   " and knowledge_cat_id = '" + knowldge.getKnowledgeCatId()+"'";
			}
			if (knowldge.getKnowledgeName() != null && !"".equals(knowldge.getKnowledgeName())) {
			   where += " and  knowledge_name like '%"+knowldge.getKnowledgeName()+"%'";
			}
			String SQL = "select *  "
			           + "  from knowledge  " 
			           +   where;
			
			System.out.println(SQL);
			
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(SQL);
		
			
			rs  = stm.executeQuery();
			System.out.println(stm);
			Knowledge stud = null;
			List<Knowledge> Knowledgelist = new ArrayList<Knowledge>();
			while (rs.next())
			{
				stud = new Knowledge();
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
				message.setList(Knowledgelist);
			}
			
			
			if(Knowledgelist == null || Knowledgelist.size() == 0) 
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



	
}
