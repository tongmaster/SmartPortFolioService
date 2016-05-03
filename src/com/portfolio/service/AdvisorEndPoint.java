package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.AdvisorDao;
import com.portfolio.model.Advisor;
import com.portfolio.model.Message;

@Path("/advisor")
public class AdvisorEndPoint {

	@Path("/register")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register(Advisor request) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(request.getAdvisorCode());
		AdvisorDao dao = new  AdvisorDao();
		Message<Advisor> advisorList = null;
		try {
			advisorList = dao.insertAdvisor(request);
			jsonObject.put("stutusCode", advisorList.getStatusCode());
			jsonObject.put("stutusMsg", advisorList.getStatusMsg());
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
		Advisor advisor  = new Advisor();
		advisor.setAdvisorCode(jsonObject.getString("code"));
		advisor.setAdvisorEmail(jsonObject.getString("email"));
		advisor.setAdvisorPassword(jsonObject.getString("password"));
		AdvisorDao dao = new  AdvisorDao();
		Message<Advisor> advisorList = null;
		JSONObject result = new JSONObject();
		try {
			advisorList = dao.checkLogin(advisor);
			result.put("stutusCode", advisorList.getStatusCode());
			result.put("stutusMsg", advisorList.getStatusMsg());
			result.put("advisor", advisorList.getList());
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
