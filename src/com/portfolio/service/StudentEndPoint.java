package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.ApprenticeDao;
import com.portfolio.dao.AttendantDao;
import com.portfolio.dao.DiagnosisDao;
import com.portfolio.dao.KnowledgeDao;
import com.portfolio.dao.StudentDao;
import com.portfolio.model.Apprentice;
import com.portfolio.model.Diagnosis;
import com.portfolio.model.DiagnosisResult;
import com.portfolio.model.Message;
import com.portfolio.model.Student;


@Path("/student")
public class StudentEndPoint {

	@Path("/register/")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register(Student student) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		StudentDao dao = new  StudentDao();
		Message<Student> studentList = null;
		try {
			studentList = dao.insertStudent(student);
		/*	if("0".equals(studentList.getStatusCode())){
				Apprentice apprentice = new Apprentice();
				apprentice.setStudentCode(student.getStudentCode());
				apprentice.setAttendantCode(student.getAttendantCode());
				apprentice.setApprenticeStartDate(student.getApprenticeStartDate());
				apprentice.setApprenticeEndDate(student.getApprenticeEndDate());
				ApprenticeDao dao1 = new  ApprenticeDao();
				Message<Apprentice> apprenticeList = dao1.insertApprentice(apprentice);
				jsonObject.put("statusCode", apprenticeList.getStatusCode());
				jsonObject.put("statusMsg", apprenticeList.getStatusMsg());
			}
			else
			{
				jsonObject.put("statusCode", studentList.getStatusCode());
				jsonObject.put("statusMsg", studentList.getStatusMsg());
			}*/
			jsonObject.put("statusCode", studentList.getStatusCode());
			jsonObject.put("statusMsg", studentList.getStatusMsg());
			
			//jsonObject.put("student", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").entity(jsonObject).build();
*/
	}
	
	@Path("/login")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response login(String login) throws JSONException {
		System.out.println(login);
		JSONObject jsonObject = new JSONObject(login);
		String code = (String)jsonObject.get("studentCode");
		//String email = (String)jsonObject.get("email");
		String password = (String)jsonObject.get("studentPassword");
		//System.out.println((String)jsonObject.get("code")+" "+(String)jsonObject.getString("email")+" "+jsonObject.getString("password"));
		Student student  = new Student();
		CharSequence cs1 = "@";
		if(code.contains(cs1))
		{
			student.setStudentEmail(code);
			student.setStudentCode("");
		}
		else
		{
			student.setStudentEmail("");
			student.setStudentCode(code);
		}
			
		//student.setStudentEmail(email);
		student.setStudentPassword(password);
		StudentDao dao = new  StudentDao();
		Message<Student> studentList = null;
		JSONObject result = new JSONObject();
		try {
			studentList = dao.checkLogin(student);
			result.put("statusCode", studentList.getStatusCode());
			result.put("statusMsg", studentList.getStatusMsg());
			result.put("student", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result.toString()).build();
/*		return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/

	}
	
	@Path("/readDiagnosis")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response readDiagnosis(String dashboard) throws JSONException {

		JSONObject jsonObject = new JSONObject(dashboard);
		//Attendant attendant  = new Attendant();
		int diagnosisId = jsonObject.getInt("diagnosisId");
		//String studentCode = jsonObject.getString("studentCode");
		DiagnosisDao dao = new  DiagnosisDao();
		
		
		Message<Diagnosis> stuList = null;
		JSONObject result = new JSONObject();
		try {
				stuList = dao.updateReadDiagnose(diagnosisId);
				result.put("statusCode",stuList.getStatusCode());
				result.put("statusMsg", stuList.getStatusMsg());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/dashboard")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response dashboard(String dashboard) throws JSONException {

		JSONObject jsonObject = new JSONObject(dashboard);
		//Attendant attendant  = new Attendant();
		String studentCode = jsonObject.getString("studentCode");
		String approve = jsonObject.getString("approve");
		String status = jsonObject.getString("status");
		//String studentCode = jsonObject.getString("studentCode");
		StudentDao dao = new  StudentDao();
		KnowledgeDao dao1 = new KnowledgeDao();
		
		
		String stuList ="0";
		String numKnow= "0";
		JSONObject result = new JSONObject();
		try {
				stuList = dao.getStudentDashBoard(studentCode,status,approve);
				numKnow = dao1.countKnowledge();
				result.put("statusCode", "0");
				result.put("statusMsg", "dashboard search ok");
				result.put("diagnosis", stuList);
				result.put("knowledge", numKnow);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/inspectlist")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response inspectlist(String diagonose) throws JSONException {

		JSONObject jsonObject = new JSONObject(diagonose);
		
		String diagnosisId = jsonObject.getString("diagnosisId");
		String studentCode = jsonObject.getString("studentCode");
		String status = jsonObject.getString("status");
		String approve = jsonObject.getString("approve");
		StudentDao dao = new  StudentDao();
		
		
		Message<DiagnosisResult> diaList = null;
		JSONObject result = new JSONObject();
		try {
			diaList = dao.inspectlist(studentCode, diagnosisId, status, approve);
			result.put("statusCode", diaList.getStatusCode());
			result.put("statusMsg", diaList.getStatusMsg());
			result.put("inspectlist", diaList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

		return Response.status(200).entity(result.toString()).build();
	}
}
