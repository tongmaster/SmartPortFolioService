package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.model.University;
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
			
			where += " order by  knowledge_createdate desc ";
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

public String countKnowledge() throws Exception {
	
	System.out.println("on method getStudentDashBoard()");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Student> message = new Message<Student>();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	String stud = "0";
    
	try {		
		/*String sql = "select s.*  from apprentice a join student s "+
			" on a.student_code = s.student_code where a.student_code = ? and a.attendant_code = ? ";*/
		String sql = "select   count(*) as amount  "
				+ "from knowledge ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		rs  = stm.executeQuery();
		
		if (rs.next())
		{
			stud = rs.getString("amount");
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
	return stud;
}


public Message<Knowledge> insertKnowledge(Knowledge knowledge) throws Exception {
	
	System.out.println("on method insertKnowledge() of Knowledge table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Knowledge> message = new Message<Knowledge>();
	try {		
		String sql = "insert into knowledge (knowledge_name , knowledge_id, knowledge_cat_id "
				+ " ,knowledge_createdate, knowledge_path , knowledge_create_name , knowledge_desc )  "+
			" values (?,?,?,?,?,?,?)";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(knowledge.getKnowledgeCreatedate());
	    Date parsedDate = dateFormat.parse(knowledge.getKnowledgeCreatedate());
	    System.out.println(">>"+parsedDate);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    System.out.println(timestamp);
	    
	    
		stm.setString(1, knowledge.getKnowledgeName());
		stm.setString(2, knowledge.getKnowledgeId());
		stm.setString(3, knowledge.getKnowledgeCatId());
		stm.setTimestamp(4, timestamp);
		stm.setString(5, knowledge.getKnowledgePath());
		stm.setString(6, knowledge.getKnowledgeCreateName());
		stm.setString(7, knowledge.getKnowledgeDesc());
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("insertKnowledge Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("insertKnowledge Completed");
			message.setStatusCode("0");
			System.out.println("=================== End insertKnowledge Complete  =====================");

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


public Message<Knowledge> updateKnowledge(Knowledge knowledge) throws Exception {
	
	System.out.println("on method updateKnowledge() of Knowledge table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Knowledge> message = new Message<Knowledge>();
	try {		
		String sql = "update knowledge set knowledge_name = ? , knowledge_cat_id = ? "
				+ " , knowledge_createdate = ?, knowledge_path = ?, knowledge_create_name = ?, knowledge_desc = ?  "+
			" where  knowledge_id = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(knowledge.getKnowledgeCreatedate());
	    Date parsedDate = dateFormat.parse(knowledge.getKnowledgeCreatedate());
	    System.out.println(">>"+parsedDate);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    System.out.println(timestamp);
	    
	    
		stm.setString(1, knowledge.getKnowledgeName());
		stm.setString(2, knowledge.getKnowledgeCatId());
		stm.setTimestamp(3, timestamp);
		stm.setString(4, knowledge.getKnowledgePath());
		stm.setString(5, knowledge.getKnowledgeCreateName());
		stm.setString(6, knowledge.getKnowledgeDesc());
		stm.setString(7, knowledge.getKnowledgeId());
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("updateKnowledge Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("updateKnowledge Completed");
			message.setStatusCode("0");
			System.out.println("=================== End updateKnowledge Complete  =====================");

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

public Message<Knowledge> deleteKnowledge(String knowledgeId) throws Exception {
	
	System.out.println("on method deleteKnowledge() of Knowledge table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Knowledge> message = new Message<Knowledge>();
	try {		
		String sql = "delete from  knowledge   "+
			"  where knowledge_id = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, knowledgeId);
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			
			message.setStatusMsg("deleteKnowledge Incorrect");
			message.setStatusCode("1");
		} else {
			message.setStatusCode("deleteKnowledge Completed");
			message.setStatusMsg("0");
			System.out.println("=================== End deleteKnowledge Complete  =====================");

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


public Message<Knowledge> findKnowledgeById(String knowledgeId) throws Exception {
	
	System.out.println("on method findKnowledge() of Knowledge table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<Knowledge> message = new Message<Knowledge>();
	try {		
		String sql = "select *  "+
			" from knowledge where knowledge_id = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, knowledgeId);
		rs  = stm.executeQuery();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Knowledge stud = null;
		List<Knowledge> Advisorlist = new ArrayList<Knowledge>();
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
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("Knowledge not  found");
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
