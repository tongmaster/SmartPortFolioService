package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.DeadLineDao;
import com.portfolio.dao.MedicalProcedureDao;
import com.portfolio.dao.UniversityDao;
import com.portfolio.model.Attendant;
import com.portfolio.model.Deadline;
import com.portfolio.model.MedicalProcedure;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.model.University;

@Path("/common")
public class CommonEndPoint {

	@Path("/getUniversity")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		University university  = new University();
		
		UniversityDao dao = new  UniversityDao();
		Message<University> universityList = null;
		try {
			universityList = dao.findUniversity();
			jsonObject.put("statusCode", universityList.getStatusCode());
			jsonObject.put("statusMsg", universityList.getStatusMsg());
			jsonObject.put("university", universityList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
	}
	
	
	@Path("/getMedicalProcedure")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response MedicalProcedure() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		MedicalProcedureDao dao = new  MedicalProcedureDao();
		Message<MedicalProcedure> MedicalProcedureList = null;
		try {
			MedicalProcedureList = dao.findMedicalProcedure();
			jsonObject.put("statusCode", MedicalProcedureList.getStatusCode());
			jsonObject.put("statusMsg", MedicalProcedureList.getStatusMsg());
			jsonObject.put("medicalProcedureList", MedicalProcedureList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
	}
	
	
	@Path("/insertMedicalProcedure")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response insertMedicalProcedure(MedicalProcedure medicalProcedure) throws JSONException {

		JSONObject resutl = new JSONObject();
		MedicalProcedureDao dao = new  MedicalProcedureDao();
		Message<MedicalProcedure> medicalProcedureList = null;
		try {
			 Message<MedicalProcedure> a = dao.findMedicalProcedure(medicalProcedure.getMdProId());
			 
			if(a.getStatusCode().equals("0"))
			{
				resutl.put("statusCode", "1");
				resutl.put("statusMsg","MedicalProcedure is exist" );
				
			}
			else
			{
				
				medicalProcedureList = dao.insertMedicalProcedure(medicalProcedure);
				resutl.put("statusCode", medicalProcedureList.getStatusCode());
				resutl.put("statusMsg", medicalProcedureList.getStatusMsg());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/updateMedicalProcedure")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response updateUniversity(MedicalProcedure medicalProcedure) throws JSONException {

		JSONObject resutl = new JSONObject();
		MedicalProcedureDao dao = new  MedicalProcedureDao();
		Message<MedicalProcedure> medicalProcedureList = null;
		try {
			
				medicalProcedureList = dao.updateMedicalProcedure(medicalProcedure);
				resutl.put("statusCode", medicalProcedureList.getStatusCode());
				resutl.put("statusMsg", medicalProcedureList.getStatusMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	
	@Path("/deleteMedicalProcedure")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response deleteMedicalProcedure(String MedicalProcedure) throws JSONException {

		JSONObject jsonObj = new JSONObject(MedicalProcedure);
		String  mdProId = jsonObj.getString("mdProId");
		JSONObject resutl = new JSONObject();
		MedicalProcedureDao dao = new  MedicalProcedureDao();
		Message<MedicalProcedure> medicalProcedureList = null;
		try {
			
			
				
				Message<MedicalProcedure> a = dao.findMedicalProcedureFromDiagnosis(mdProId);
				 
				if(a.getStatusCode().equals("0") )
				{
					resutl.put("statusCode", "1");
					resutl.put("statusMsg","MedicalProcedure is used of Diagnosis" );
					
				}
				else
				{
					
					medicalProcedureList = dao.deleteMedicalProcedure(mdProId);
					resutl.put("statusCode", medicalProcedureList.getStatusCode());
					resutl.put("statusMsg", medicalProcedureList.getStatusMsg());
				}
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/insertDeadLine")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response deadline(String dead) throws JSONException {

		JSONObject jsonObject = new JSONObject(dead);
		JSONObject resutl = new JSONObject();
		DeadLineDao dao = new  DeadLineDao();
		Message<Deadline> MedicalProcedureList = null;
		try {
			Deadline deadline = new Deadline();
			deadline.setAttendentCode((String)jsonObject.getString("attendantCode"));
			deadline.setDeadline1((String)jsonObject.getString("deadline1"));
			MedicalProcedureList = dao.insertDeadLine(deadline);
			resutl.put("statusCode", MedicalProcedureList.getStatusCode());
			resutl.put("statusMsg", MedicalProcedureList.getStatusMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	

	@Path("/insertUniversity")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response insertUniversity(University university) throws JSONException {

		JSONObject resutl = new JSONObject();
		UniversityDao dao = new  UniversityDao();
		Message<University> universityList = null;
		try {
			 Message<University> a = dao.findUniversity(university.getUniversityCode());
			 
			if(a.getStatusCode().equals("0"))
			{
				resutl.put("statusCode", "1");
				resutl.put("statusMsg","university is exist" );
				
			}
			else
			{
				
				universityList = dao.insertUniversity(university);
				resutl.put("statusCode", universityList.getStatusCode());
				resutl.put("statusMsg", universityList.getStatusMsg());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/updateUniversity")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response updateUniversity(University university) throws JSONException {

		JSONObject resutl = new JSONObject();
		UniversityDao dao = new  UniversityDao();
		Message<University> universityList = null;
		try {
			
				universityList = dao.updateUniversity(university);
				resutl.put("statusCode", universityList.getStatusCode());
				resutl.put("statusMsg", universityList.getStatusMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/deleteUniversity")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response deleteUniversity(String university) throws JSONException {

		JSONObject jsonObj = new JSONObject(university);
		String  universityCode = jsonObj.getString("universityCode");
		JSONObject resutl = new JSONObject();
		UniversityDao dao = new  UniversityDao();
		Message<University> universityList = null;
		try {
			
			
				
				Message<Student> a = dao.findUniversityFromStudent(universityCode);
				Message<Attendant> b = dao.findUniversityFromAttendant(universityCode);
				 
				if(a.getStatusCode().equals("0")  || b.getStatusCode().equals("0"))
				{
					resutl.put("statusCode", "1");
					resutl.put("statusMsg","university is used of student or attendant" );
					
				}
				else
				{
					
					universityList = dao.deleteUniversity(universityCode);
					resutl.put("statusCode", universityList.getStatusCode());
					resutl.put("statusMsg", universityList.getStatusMsg());
				}
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
}
