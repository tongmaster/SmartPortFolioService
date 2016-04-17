package com.portfolio.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.AdvisorDao;
import com.portfolio.model.Message;
import com.portfolio.model.Advisor;

@Path("/advisor")
public class AdvisorEndPoint {

	@Path("/register/{code}/{email}/{password}")
	@GET
	@Produces("application/json")
	public Response Register(@PathParam("code") String code,@PathParam("email") String email,@PathParam("password") String password) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(code+" "+email+" "+password);
		Advisor advisor  = new Advisor();
		advisor.setAdvisorCode(code);
		advisor.setAdvisorEmail(email);
		advisor.setAdvisorPassword(password);
		AdvisorDao dao = new  AdvisorDao();
		Message<Advisor> advisorList = null;
		try {
			advisorList = dao.insertAdvisor(advisor);
			jsonObject.put("stutusCode", advisorList.getStatusCode());
			jsonObject.put("stutusMsg", advisorList.getStatusMsg());
			jsonObject.put("advisor", advisorList.getList());
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
		Advisor advisor  = new Advisor();
		advisor.setAdvisorCode(code);
		advisor.setAdvisorEmail(email);
		advisor.setAdvisorPassword(password);
		AdvisorDao dao = new  AdvisorDao();
		Message<Advisor> advisorList = null;
		try {
			advisorList = dao.checkLogin(advisor);
			jsonObject.put("stutusCode", advisorList.getStatusCode());
			jsonObject.put("stutusMsg", advisorList.getStatusMsg());
			jsonObject.put("advisor", advisorList.getList());
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
