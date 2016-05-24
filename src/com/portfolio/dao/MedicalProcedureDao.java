package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.portfolio.model.MedicalProcedure;
import com.portfolio.model.Message;
import com.portfolio.model.University;
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




}
