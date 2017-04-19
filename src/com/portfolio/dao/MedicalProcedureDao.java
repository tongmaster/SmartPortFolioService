package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.Attendant;
import com.portfolio.model.MedicalProcedure;
import com.portfolio.model.Message;
import com.portfolio.model.University;
import com.portfolio.model.MedicalProcedure;
import com.portfolio.util.ConnectionHelper;



public class MedicalProcedureDao {
	

	
public Message<MedicalProcedure> findMedicalProcedure() throws Exception {
		
		System.out.println("on method medical_procedure() of medical_procedure table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<MedicalProcedure> message = new Message<MedicalProcedure>();
		try {		
			String sql = "select *  "+
				" from medical_procedure  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			rs  = stm.executeQuery();
			MedicalProcedure stud = null;
			List<MedicalProcedure> MedicalProcedurelist = new ArrayList<MedicalProcedure>();
			while (rs.next())
			{
				stud = new MedicalProcedure();
				stud.setMdProId(rs.getString("md_pro_id"));
				stud.setMdProName(rs.getString("md_pro_name"));
				stud.setMdQuantity(rs.getInt("md_quantity"));
				
				message.setStatusCode("0");
				message.setStatusMsg("medical_procedure found");
				MedicalProcedurelist.add(stud);
				message.setList(MedicalProcedurelist);
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

public Message<MedicalProcedure> findMedicalProcedure(String universityCode) throws Exception {
	
	System.out.println("on method findMedicalProcedure() of medical_procedure table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<MedicalProcedure> message = new Message<MedicalProcedure>();
	try {		
		String sql = "select *  "+
			" from medical_procedure where md_pro_id = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, universityCode);
		rs  = stm.executeQuery();
		MedicalProcedure stud = null;
		List<MedicalProcedure> Advisorlist = new ArrayList<MedicalProcedure>();
		while (rs.next())
		{
			stud = new MedicalProcedure();
			stud.setMdProId(rs.getString("md_pro_id"));
			stud.setMdProName(rs.getString("md_pro_name"));
			stud.setMdQuantity(rs.getInt("md_quantity"));
			
			message.setStatusCode("0");
			message.setStatusMsg("MedicalProcedure found");
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("MedicalProcedure not  found");
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


public Message<MedicalProcedure> insertMedicalProcedure(MedicalProcedure MedicalProcedure) throws Exception {
	
	System.out.println("on method insertMedicalProcedure() of MedicalProcedure table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<MedicalProcedure> message = new Message<MedicalProcedure>();
	try {		
		String sql = "insert into medical_procedure (md_pro_id , md_pro_name , md_quantity )  "+
			" values (?,?,?)";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, MedicalProcedure.getMdProId());
		stm.setString(2, MedicalProcedure.getMdProName());
		stm.setInt(3, MedicalProcedure.getMdQuantity());
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("insertMedicalProcedure Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("insertMedicalProcedure Completed");
			message.setStatusCode("0");
			System.out.println("=================== End insertMedicalProcedure Complete  =====================");

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


public Message<MedicalProcedure> updateMedicalProcedure(MedicalProcedure MedicalProcedure) throws Exception {
	
	System.out.println("on method updateMedicalProcedure() of medical_procedure table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<MedicalProcedure> message = new Message<MedicalProcedure>();
	try {		
		String sql = "update medical_procedure set md_pro_name = ?  , md_quantity = ? "+
			"  where md_pro_id = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		
		stm.setString(1, MedicalProcedure.getMdProName());
		stm.setInt(2, MedicalProcedure.getMdQuantity());
		stm.setString(3, MedicalProcedure.getMdProId());
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("updateMedicalProcedure Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("updateMedicalProcedure Completed");
			message.setStatusCode("0");
			System.out.println("=================== End updateMedicalProcedure Complete  =====================");

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

public Message<MedicalProcedure> deleteMedicalProcedure(String mdProId) throws Exception {
	
	System.out.println("on method deleteMedicalProcedure() of medical_procedure table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<MedicalProcedure> message = new Message<MedicalProcedure>();
	try {		
		String sql = "delete from  medical_procedure   "+
			"  where md_pro_id = ? ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, mdProId);
		
		stm.executeUpdate();

		int rows = stm.getUpdateCount();
		System.out.println(stm);
		System.out.println("rows = " + rows);
		if (rows == 0) {
			message.setStatusCode("deleteMedicalProcedure Incorrect");
			message.setStatusMsg("1");
		} else {
			message.setStatusMsg("deleteMedicalProcedure Completed");
			message.setStatusCode("0");
			System.out.println("=================== End deleteMedicalProcedure Complete  =====================");

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

public Message<MedicalProcedure> findMedicalProcedureFromDiagnosis(String mdProId) throws Exception {
	
	System.out.println("on method findMedicalProcedureFromDiagnosis() of MedicalProcedure table");
	Connection conn = null;
	ResultSet rs ;
	PreparedStatement stm ;
	Message<MedicalProcedure> message = new Message<MedicalProcedure>();
	try {		
		String sql = "select *  "+
			" from diagnosis where md_pro_id = ?  ";
		conn = ConnectionHelper.getConnection();
		stm = conn.prepareStatement(sql);
		stm.setString(1, mdProId);
		rs  = stm.executeQuery();
		MedicalProcedure stud = null;
		List<MedicalProcedure> Advisorlist = new ArrayList<MedicalProcedure>();
		while (rs.next())
		{
			stud = new MedicalProcedure();
			stud.setMdProId(rs.getString("md_pro_id"));
			message.setStatusCode("0");
			message.setStatusMsg("MedicalProcedure of Diagnosis found");
			Advisorlist.add(stud);
			message.setList(Advisorlist);
		}
		
		
		if(Advisorlist == null || Advisorlist.size() == 0)
		{
			message.setStatusCode("1");
			message.setStatusMsg("MedicalProcedure of Diagnosis not  found");
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
