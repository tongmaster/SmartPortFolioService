package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.KnowledgeDao;
import com.portfolio.dao.UniversityDao;
import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.University;


@Path("/knowledge")
public class KnowledgeEndPoint {

	
	
	@Path("/findKnowledge")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response login(String knowledge) throws JSONException {

		JSONObject jsonObject = new JSONObject(knowledge);
		String knowledgeName = (String)jsonObject.get("knowledgeName");
		String knowledgeCatId = (String)jsonObject.get("knowledgeCatId");
		String knowledgeStartDate = (String)jsonObject.get("knowledgeStartDate");
		String knowledgeEndDate = (String)jsonObject.get("knowledgeEndDate");
		String studentid = (String)jsonObject.get("studentid");
		Knowledge Knowledge  = new Knowledge();
		Knowledge.setKnowledgeName(knowledgeName);
		Knowledge.setKnowledgeCatId(knowledgeCatId);
		KnowledgeDao dao = new  KnowledgeDao();
		Message<Knowledge> KnowledgeList = null;
		JSONObject result = new JSONObject();
		try {
			KnowledgeList = dao.findKnowledge(Knowledge,knowledgeStartDate,knowledgeEndDate);
			result.put("statusCode", KnowledgeList.getStatusCode());
			result.put("statusMsg", KnowledgeList.getStatusMsg());
			result.put("knowledge", KnowledgeList.getList());
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
	
	
	@Path("/insertKnowledge")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response insertUniversity(Knowledge knowledge) throws JSONException {

		JSONObject resutl = new JSONObject();
		KnowledgeDao dao = new  KnowledgeDao();
		Message<Knowledge> knowledgeList = null;
		try {
			 Message<Knowledge> a = dao.findKnowledgeById(knowledge.getKnowledgeId());
			 
			if(a.getStatusCode().equals("0"))
			{
				resutl.put("statusCode", "1");
				resutl.put("statusMsg","knowledge is exist" );
				
			}
			else
			{
				
				knowledgeList = dao.insertKnowledge(knowledge);
				resutl.put("statusCode", knowledgeList.getStatusCode());
				resutl.put("statusMsg", knowledgeList.getStatusMsg());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/updateKnowledge")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response updateUniversity(Knowledge knowledge) throws JSONException {

		JSONObject resutl = new JSONObject();
		KnowledgeDao dao = new  KnowledgeDao();
		Message<Knowledge> knowledgeList = null;
		try {
			
				knowledgeList = dao.updateKnowledge(knowledge);
				resutl.put("statusCode", knowledgeList.getStatusCode());
				resutl.put("statusMsg", knowledgeList.getStatusMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
	
	@Path("/deleteKnowledge")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response deleteUniversity(String knowledge) throws JSONException {

		JSONObject jsonObj = new JSONObject(knowledge);
		String  knowledgeId = jsonObj.getString("knowledgeId");
		JSONObject resutl = new JSONObject();
		KnowledgeDao dao = new  KnowledgeDao();
		Message<Knowledge> knowledgeList = null;
		try {
				knowledgeList = dao.deleteKnowledge(knowledgeId);
				resutl.put("statusCode", knowledgeList.getStatusCode());
				resutl.put("statusMsg", knowledgeList.getStatusMsg());
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resutl.put("statusCode", "-1");
			resutl.put("statusMsg", e.getMessage());
		}
		
		
		return Response.status(200).entity(resutl.toString()).build();

	}
}
