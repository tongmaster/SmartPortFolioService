package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.DiagnosisDao;
import com.portfolio.model.Diagnosis;
import com.portfolio.model.Message;

@Path("/diagnosis")
public class DiagnosisEndPoint {

	@Path("/patient/")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response DiagnosisPatient(Diagnosis diagnosis) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(diagnosis.getPatientFirstName()+" "+diagnosis.getSymptoms()+" "+diagnosis.getTreatment()+" "+diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		
		DiagnosisDao dao = new  DiagnosisDao();
		Message<Diagnosis> diagList = null;
		try {
			diagList = dao.insertDiagnosis(diagnosis);
			jsonObject.put("stutusCode", diagList.getStatusCode());
			jsonObject.put("stutusMsg", diagList.getStatusMsg());
			jsonObject.put("diagnosis", diagList.getList());
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
