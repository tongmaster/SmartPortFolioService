package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.AdminDao;
import com.portfolio.model.Admin;
import com.portfolio.model.Message;

@Path("/admin")
public class AdminEndPoint {

	@Path("/login")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response login(String login) throws JSONException {
		System.out.println(login);
		JSONObject jsonObject = new JSONObject(login);
		String username = (String)jsonObject.get("username");
		//String email = (String)jsonObject.get("email");
		String password = (String)jsonObject.get("password");
		//System.out.println((String)jsonObject.get("code")+" "+(String)jsonObject.getString("email")+" "+jsonObject.getString("password"));
		Admin admin  = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);;
		AdminDao dao = new  AdminDao();
		Message<Admin> studentList = null;
		JSONObject result = new JSONObject();
		try {
			studentList = dao.checkLogin(admin);
			result.put("statusCode", studentList.getStatusCode());
			result.put("statusMsg", studentList.getStatusMsg());
			result.put("admin", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(result.toString()).build();


	}

	
}
