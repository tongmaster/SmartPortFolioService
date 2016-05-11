package com.portfolio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.KnowledgeDao;
import com.portfolio.dao.StudentDao;
import com.portfolio.model.Knowledge;
import com.portfolio.model.Message;
import com.portfolio.model.Student;


@Path("/knowledge")
public class KnowledgeEndPoint {

	@Path("/insertKnowledge/")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response Register(Student student) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		StudentDao dao = new  StudentDao();
		Message<Student> studentList = null;
		try {
			studentList = dao.insertStudent(student);
			jsonObject.put("stutusCode", studentList.getStatusCode());
			jsonObject.put("stutusMsg", studentList.getStatusMsg());
			//jsonObject.put("student", studentList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("stutusCode", "-1");
			jsonObject.put("stutusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").entity(jsonObject).build();
*/
	}
	
	@Path("/findKnowledge")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response login(String knowledge) throws JSONException {

		JSONObject jsonObject = new JSONObject(knowledge);
		String knowledgeName = (String)jsonObject.get("knowledgeName");
		String knowledgeId = (String)jsonObject.get("knowledgeId");
		String knowledgeCreateDate = (String)jsonObject.get("knowledgeCreateDate");
		Knowledge Knowledge  = new Knowledge();
		Knowledge.setKnowledgeName(knowledgeName);
		Knowledge.setKnowledgeId(knowledgeId);
		Knowledge.setKnowledgeCreatedate(knowledgeCreateDate);
		KnowledgeDao dao = new  KnowledgeDao();
		Message<Knowledge> KnowledgeList = null;
		JSONObject result = new JSONObject();
		try {
			KnowledgeList = dao.findKnowledge(Knowledge);
			result.put("statusCode", KnowledgeList.getStatusCode());
			result.put("statusMsg", KnowledgeList.getStatusMsg());
			result.put("knowledge", KnowledgeList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("stutusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result.toString()).build();
/*		return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/

	}
}
