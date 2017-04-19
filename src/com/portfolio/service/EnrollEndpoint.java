package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.model.MyJaxBean;
 
@Path("/test")
public class EnrollEndpoint {
	
	@Path("/xml")
	@GET
	@Produces("application/xml")
	public String convertCtoF() {
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
 
	@Path("xml/{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
	
	@Path("/json")
	@GET
	  @Produces("application/json")
	  public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32)*5/9; 
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);

		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }

	  @Path("json/{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (f - 32)*5/9; 
		jsonObject.put("F Value", f); 
		jsonObject.put("C Value", celsius);

		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	  
	  @POST
	  @Path("/create")
	  public Response create(@FormParam("param1") String param1,
	                     @FormParam("param2") String param2) {
		  JSONObject jsonObject = new JSONObject();
			jsonObject.put("F Value", param1); 
			jsonObject.put("C Value", param2);

			String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
			return Response.status(200).entity(jsonObject.toString()).build();
	  }
	  
	  @POST @Consumes("application/json")
	  @Path("/createe")
	  public Response create(MyJaxBean input) {
	      System.out.println("param1 = " + input.param1);
	      System.out.println("param2 = " + input.param2);
	      JSONObject jsonObject = new JSONObject();
			jsonObject.put("F Value", input.param1); 
			jsonObject.put("C Value", input.param2);

			String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
			return Response.status(200).entity(jsonObject).build();
	  }

}
