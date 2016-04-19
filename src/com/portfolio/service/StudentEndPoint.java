package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.StudentDao;
import com.portfolio.model.Message;
import com.portfolio.model.Student;

@Path("/student")
public class StudentEndPoint {

	@Path("/register/")
	@POST
	@Produces("application/json")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register(Student student) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		StudentDao dao = new  StudentDao();
		Message<Student> studentList = null;
		try {
			studentList = dao.insertStudent(student);
			jsonObject.put("stutusCode", studentList.getStatusCode());
			jsonObject.put("stutusMsg", studentList.getStatusMsg());
			jsonObject.put("student", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("stutusCode", "-1");
			jsonObject.put("stutusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@Path("/login/{code}/{email}/{password}")
	@GET
	@Produces("application/json")
	public Response login(@PathParam("code") String code,@PathParam("email") String email,@PathParam("password") String password) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(code+" "+email+" "+password);
		Student student  = new Student();
		student.setStudentCode(code);
		student.setStudentEmail(email);
		student.setStudentPassword(password);
		StudentDao dao = new  StudentDao();
		Message<Student> studentList = null;
		try {
			studentList = dao.checkLogin(student);
			jsonObject.put("stutusCode", studentList.getStatusCode());
			jsonObject.put("stutusMsg", studentList.getStatusMsg());
			jsonObject.put("student", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("stutusCode", "-1");
			jsonObject.put("stutusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	}
}
