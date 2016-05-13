package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.AttendantDao;
import com.portfolio.model.Attendant;
import com.portfolio.model.Message;

@Path("/attendant")
public class AttendantEndPoint {

	@Path("/register")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register(Attendant request) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(request.getAttendantCode());
		AttendantDao dao = new  AttendantDao();
		Message<Attendant> attendantList = null;
		try {
			attendantList = dao.insertAttendant(request);
			jsonObject.put("stutusCode", attendantList.getStatusCode());
			jsonObject.put("stutusMsg", attendantList.getStatusMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("stutusCode", "-1");
			jsonObject.put("stutusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
	}
	
	@Path("/login")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response login(String login) throws JSONException {

		JSONObject jsonObject = new JSONObject(login);
		Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		String attendantPassword = jsonObject.getString("attendantPassword");
		AttendantDao dao = new  AttendantDao();
		
		CharSequence cs1 = "@";
		if(attendantCode.contains(cs1))
		{
			attendant.setAttendantEmail(attendantCode);
			attendant.setAttendantCode("");
		}
		else
		{
			attendant.setAttendantEmail("");
			attendant.setAttendantCode(attendantCode);
		}
		attendant.setAttendantPassword(attendantPassword);
		Message<Attendant> attendantList = null;
		JSONObject result = new JSONObject();
		try {
			attendantList = dao.checkLogin(attendant);
			result.put("stutusCode", attendantList.getStatusCode());
			result.put("stutusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("stutusCode", "-1");
			result.put("stutusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		/*return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
		return Response.status(200).entity(result.toString()).build();
	}
}
