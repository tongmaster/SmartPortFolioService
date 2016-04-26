package com.portfolio.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.UniversityDao;
import com.portfolio.model.Message;
import com.portfolio.model.University;

@Path("/common")
public class CommonEndPoint {

	@Path("/getUniversity")
	@GET
	@Produces("application/json")
	public Response Register() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		University university  = new University();
		
		UniversityDao dao = new  UniversityDao();
		Message<University> universityList = null;
		try {
			universityList = dao.findUniversity();
			jsonObject.put("stutusCode", universityList.getStatusCode());
			jsonObject.put("stutusMsg", universityList.getStatusMsg());
			jsonObject.put("university", universityList.getList());
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
