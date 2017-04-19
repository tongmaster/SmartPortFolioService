package com.portfolio.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.AttendantDao;
import com.portfolio.dao.DeadLineDao;
import com.portfolio.dao.DiagnosisDao;
import com.portfolio.dao.UniversityDao;
import com.portfolio.model.Attendant;
import com.portfolio.model.Deadline;
import com.portfolio.model.Diagnosis;
import com.portfolio.model.DiagnosisResult;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.model.University;
import com.portfolio.util.ConnectionHelper;

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
		DeadLineDao dao1 = new  DeadLineDao();
		Message<Attendant> attendantList = null;
		try {
			attendantList = dao.insertAttendant(request);
			if("0".equals(attendantList.getStatusCode()))
			{
				Deadline dead = new Deadline();
				dead.setAttendentCode(request.getAttendantCode());
				dead.setDeadline1(request.getDeadline1());
				Message<Deadline> deadlineList = dao1.insertDeadLine(dead);
				jsonObject.put("statusCode", deadlineList.getStatusCode());
				jsonObject.put("statusMsg", deadlineList.getStatusMsg());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
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
			result.put("statusCode", attendantList.getStatusCode());
			result.put("statusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		/*return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/getAttendant")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getAttendant() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		AttendantDao dao = new  AttendantDao();
		Message<Attendant> AttendantList = null;
		try {
			AttendantList = dao.getAttendant();
			jsonObject.put("statusCode", AttendantList.getStatusCode());
			jsonObject.put("statusMsg", AttendantList.getStatusMsg());
			jsonObject.put("AttendantList", AttendantList.getList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	/*	return Response.status(200).entity(jsonObject.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST,GET,PUT,UPDATE,OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type,Accept,X-Requested-With").build();*/
	}
	/*
	@Path("/dashboard")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response dashboard(String dashboard) throws JSONException {

		JSONObject jsonObject = new JSONObject(dashboard);
		//Attendant attendant  = new Attendant();
		//String attendantCode = jsonObject.getString("attendantCode");
		String studentCode = jsonObject.getString("studentCode");
		AttendantDao dao = new  AttendantDao();
		
		
		Message<Student> stuList = null;
		JSONObject result = new JSONObject();
		try {
				if(studentCode.equals("A"))
				{
					stuList = dao.getStudentDashBoardAll();
					result.put("statusCode", stuList.getStatusCode());
					result.put("statusMsg", stuList.getStatusMsg());
					result.put("student", stuList.getList());
				}
				else
				{
					stuList = dao.getStudentDashBoard(studentCode);
					result.put("statusCode", stuList.getStatusCode());
					result.put("statusMsg", stuList.getStatusMsg());
					result.put("student", stuList.getList());
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	*/
	
	@Path("/dashboard")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response dashboard(String dashboard) throws JSONException {

		JSONObject jsonObject = new JSONObject(dashboard);
		//Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		//String studentCode = jsonObject.getString("studentCode");
		AttendantDao dao = new  AttendantDao();
		
		
		Message<Student> stuList = null;
		JSONObject result = new JSONObject();
		try {
				stuList = dao.getAttendantDashBoard(attendantCode);
				result.put("statusCode", stuList.getStatusCode());
				result.put("statusMsg", stuList.getStatusMsg());
				result.put("student", stuList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	/*
	@Path("/getDiagonose")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getDiagonose(String diagonose) throws JSONException {

		JSONObject jsonObject = new JSONObject(diagonose);
		
		//String attendantCode = jsonObject.getString("attendantCode");
		String studentCode = jsonObject.getString("studentCode");
		DiagnosisDao dao = new  DiagnosisDao();
		
		
		Message<DiagnosisResult> diaList = null;
		JSONObject result = new JSONObject();
		try {
			
				if(studentCode.equals("A"))
				{
					diaList = dao.getDiagonosisAll();
					result.put("statusCode", diaList.getStatusCode());
					result.put("statusMsg", diaList.getStatusMsg());
					result.put("diagosisResult", diaList.getList());
				}
				else
				{
					diaList = dao.getDiagonosis(studentCode);
					result.put("statusCode", diaList.getStatusCode());
					result.put("statusMsg", diaList.getStatusMsg());
					result.put("diagosisResult", diaList.getList());
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

		return Response.status(200).entity(result.toString()).build();
	}
	*/
	
	@Path("/getDiagonose")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getDiagonose(String diagonose) throws JSONException {

		JSONObject jsonObject = new JSONObject(diagonose);
		
		String attendantCode = jsonObject.getString("attendantCode");
		String studentCode = jsonObject.getString("studentCode");
		DiagnosisDao dao = new  DiagnosisDao();
		
		
		Message<DiagnosisResult> diaList = null;
		JSONObject result = new JSONObject();
		try {
			diaList = dao.getDiagonosis(attendantCode,studentCode);
			result.put("statusCode", diaList.getStatusCode());
			result.put("statusMsg", diaList.getStatusMsg());
			result.put("diagosisResult", diaList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/getRating")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getRating(String diagonose) throws JSONException {

		JSONObject jsonObject = new JSONObject(diagonose);
		
		int diagnosisId = jsonObject.getInt("diagnosisId");
		DiagnosisDao dao = new  DiagnosisDao();
		
		
		Message<DiagnosisResult> diaList = null;
		JSONObject result = new JSONObject();
		try {
			diaList = dao.getDiagonosisById(diagnosisId);
			result.put("statusCode", diaList.getStatusCode());
			result.put("statusMsg", diaList.getStatusMsg());
			result.put("getRating", diaList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

		return Response.status(200).entity(result.toString()).build();
	}
	
	
	@Path("/ratingResult")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response ratingResult(String ratingResult) throws JSONException {

		JSONObject jsonObject = new JSONObject(ratingResult);
		//Attendant attendant  = new Attendant();
		int diagnosisId = jsonObject.getInt("diagnosisId");
		int rating = jsonObject.getInt("rating");
		String dignosisSuggestion = jsonObject.getString("dignosisSuggestion");
		DiagnosisDao dao = new  DiagnosisDao();
		
		
		Message<Diagnosis> stuList = null;
		JSONObject result = new JSONObject();
		try {
				stuList = dao.insertRating(diagnosisId,rating,dignosisSuggestion);
				result.put("statusCode", stuList.getStatusCode());
				result.put("statusMsg", stuList.getStatusMsg());
				//result.put("student", stuList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/getDelayReport")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getDelayReport(String delayReport) throws JSONException {

		JSONObject jsonObject = new JSONObject(delayReport);
		//Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		String approve = jsonObject.getString("approve");
		AttendantDao dao = new  AttendantDao();
		
		
		Message<DiagnosisResult> stuList = null;
		JSONObject result = new JSONObject();
		try {
				stuList = dao.getDelayReport(attendantCode, approve);
				result.put("statusCode", stuList.getStatusCode());
				result.put("statusMsg", stuList.getStatusMsg());
				result.put("getDelayReport", stuList.getList());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	public static void main(String[] args) {
		JSONArray rootData = new JSONArray();
		JSONArray rootData2 = new JSONArray();
		JSONArray nodeData = null;
		JSONObject obj = new JSONObject();
		for (int i = 0; i < 3; i++) {
			nodeData = new JSONArray();
			nodeData.put("a");
			nodeData.put("b");
			nodeData.put("c");
			//nodeUniversity.put("unversity"+i);
			rootData.put(nodeData);
			rootData2.put("unversity"+i);
		}
		
		obj.put("data", rootData);
		obj.put("university",rootData2);
		System.out.println(obj);
	}
	
	@Path("/getGraphReport")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getGraphReport(String graph) throws JSONException {

		JSONObject jsonObject = new JSONObject(graph);
		JSONObject result = new JSONObject();
		JSONArray allData = new JSONArray();
		JSONArray rootData = new JSONArray();
		JSONArray rootUniversity = new JSONArray();
		JSONArray nodeData = null;
		//JSONObject resultSub = null;
		//Attendant attendant  = new Attendant();
		int monthStart = jsonObject.getInt("monthStart");
		int monthEnd = jsonObject.getInt("monthEnd");
		int year = jsonObject.getInt("year");
		//String startDate = (String)jsonObject.get("startDate");
		//String endDate = (String)jsonObject.get("endDate");
		String universityCode = jsonObject.getString("universityCode");
		String attendantCode = jsonObject.getString("attendantCode");
		UniversityDao dao1 = new  UniversityDao();
		DiagnosisDao dao = new DiagnosisDao();
		
		try {
		
		if(universityCode.equals("A"))
		{
			Message<University> university= dao1.findUniversity();
			List yearmonth = ConnectionHelper.getBetweenMonthForYear(monthStart, monthEnd, year);
			for (int j = 0; j < yearmonth.size(); j++) {
				 String my = (String)yearmonth.get(j);
				 String[] arryMontnYear = my.split("/");
				if(university != null && university.getList().size() > 0){
					
					for (int i = 0; i < university.getList().size(); i++) {
						int send = dao.getGraphReportDataSend(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
						int read = dao.getGraphReportDataRead(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
						int inspect = dao.getGraphReportDataInspect(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
						nodeData = new JSONArray();
						nodeData.put(send);
						nodeData.put(read);
						nodeData.put(inspect);
						rootData.put(nodeData);
						rootUniversity.put(university.getList().get(0).getUniversityName());
					}
					result.put("showDate",my);
					result.put("data", rootData);
					result.put("university",rootUniversity);
					allData.put(result);
				}
			}
			result.put("statusCode", "0");
			result.put("statusMsg", "Graph Report ok");
			result.put("getGraph", allData);
		}
		else
		{
			Message<University> university= dao1.findUniversity(universityCode);
			List yearmonth = ConnectionHelper.getBetweenMonthForYear(monthStart, monthEnd, year);
			for (int j = 0; j < yearmonth.size(); j++) {
				 String my = (String)yearmonth.get(j);
				 String[] arryMontnYear = my.split("/");
				 if(university != null && university.getList().size() > 0){
						for (int i = 0; i < university.getList().size(); i++) {
							int send = dao.getGraphReportDataSend(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
							int read = dao.getGraphReportDataRead(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
							int inspect = dao.getGraphReportDataInspect(Integer.parseInt(arryMontnYear[0]), year, attendantCode, universityCode);
							nodeData = new JSONArray();
							nodeData.put(send);
							nodeData.put(read);
							nodeData.put(inspect);
							rootData.put(nodeData);
							rootUniversity.put(university.getList().get(0).getUniversityName());
							
						}
						result.put("showDate",my);
						result.put("data", rootData);
						result.put("university",rootUniversity);
						allData.put(result);
					}
			}
			result.put("statusCode", "0");
			result.put("statusMsg", "Graph Report ok");
			result.put("getGraph", allData);
		}
		/*attendant.setAttendantPassword(attendantPassword);
		Message<Attendant> attendantList = null;
		
			attendantList = dao.checkLogin(attendant);
			result.put("statusCode", attendantList.getStatusCode());
			result.put("statusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		

		return Response.status(200).entity(result.toString()).build();
	}
	
	@Path("/getSummaryReport")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getSummaryReport(String summary) throws JSONException {

		JSONObject jsonObject = new JSONObject(summary);
		JSONObject result = new JSONObject();
		JSONArray n = new JSONArray();
		JSONObject resultSub = null;
		Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		String universityCode = jsonObject.getString("universityCode");
		String startDate = (String)jsonObject.get("startDate");
		String endDate = (String)jsonObject.get("endDate");
		AttendantDao dao = new  AttendantDao();
		UniversityDao dao1 = new  UniversityDao();
		try {
		
		if(universityCode.equals("A"))
		{
			Message<University> university= dao1.findUniversity();
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					Message<DiagnosisResult> b= dao.getSummaryReport(attendantCode, university.getList().get(i).getUniversityCode(), startDate, endDate);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					if("0".equals(b.getStatusCode())){
						System.out.println(">>"+b.getList().size() );
						resultSub.put("universityList", b.getList());
						
					}
					else
					{
						resultSub.put("universityList", "");
					}
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "Summary Reprot ok");
				result.put("summaryReport", n);
			}
			
		}
		else
		{
			Message<University> university= dao1.findUniversity(universityCode);
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					Message<DiagnosisResult> b= dao.getSummaryReport(attendantCode, university.getList().get(i).getUniversityCode(), startDate, endDate);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					if("0".equals(b.getStatusCode())){
						System.out.println(">>"+b.getList().size() );
						resultSub.put("universityList", b.getList());
						
					}
					else
					{
						resultSub.put("universityList", "");
					}
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "Summary Reprot ok");
				result.put("summaryReport", n);
			}
		}
		/*attendant.setAttendantPassword(attendantPassword);
		Message<Attendant> attendantList = null;
		
			attendantList = dao.checkLogin(attendant);
			result.put("statusCode", attendantList.getStatusCode());
			result.put("statusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		

		return Response.status(200).entity(result.toString()).build();
	}
	
	
	@Path("/getOverallReport")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getOverallReport(String summary) throws JSONException {

		JSONObject jsonObject = new JSONObject(summary);
		JSONObject result = new JSONObject();
		JSONArray n = new JSONArray();
		JSONObject resultSub = null;
		Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		String universityCode = jsonObject.getString("universityCode");
		/*String startDate = (String)jsonObject.get("startDate");
		String endDate = (String)jsonObject.get("endDate");*/
		AttendantDao dao = new  AttendantDao();
		UniversityDao dao1 = new  UniversityDao();
		try {
		
		if(universityCode.equals("A"))
		{
			Message<University> university= dao1.findUniversity();
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					int  studentAmount = dao.amountStudentForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  handInAmount = dao.amountHandInForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  IsReadAmount = dao.amountIsReadForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  DiagnosisAmount = dao.amountDianosisForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					resultSub.put("amountStudents", studentAmount);
					resultSub.put("amountHandIn", handInAmount);
					resultSub.put("amountIsRead", IsReadAmount);
					resultSub.put("amountDisgnosis", DiagnosisAmount);
					
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "OverAll Reprot ok");
				result.put("OverAllReport", n);
			}
			
		}
		else
		{
			Message<University> university= dao1.findUniversity(universityCode);
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					int  studentAmount = dao.amountStudentForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  handInAmount = dao.amountHandInForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  IsReadAmount = dao.amountIsReadForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					int  DiagnosisAmount = dao.amountDianosisForOverall(university.getList().get(i).getUniversityCode(),attendantCode);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					resultSub.put("amountStudents", studentAmount);
					resultSub.put("amountHandIn", handInAmount);
					resultSub.put("amountIsRead", IsReadAmount);
					resultSub.put("amountDisgnosis", DiagnosisAmount);
					
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "OverAll Reprot ok");
				result.put("summaryReport", n);
			}
		}
		/*attendant.setAttendantPassword(attendantPassword);
		Message<Attendant> attendantList = null;
		
			attendantList = dao.checkLogin(attendant);
			result.put("statusCode", attendantList.getStatusCode());
			result.put("statusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		

		return Response.status(200).entity(result.toString()).build();
	}
	
	
	@Path("/getConfidenceReport")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getConfidenceReport(String summary) throws JSONException {

		JSONObject jsonObject = new JSONObject(summary);
		JSONObject result = new JSONObject();
		JSONArray n = new JSONArray();
		JSONObject resultSub = null;
		Attendant attendant  = new Attendant();
		String attendantCode = jsonObject.getString("attendantCode");
		String universityCode = jsonObject.getString("universityCode");
		String startDate = jsonObject.getString("startDate");
		String endDate = jsonObject.getString("endDate");
		/*String startDate = (String)jsonObject.get("startDate");
		String endDate = (String)jsonObject.get("endDate");*/
		AttendantDao dao = new  AttendantDao();
		UniversityDao dao1 = new  UniversityDao();
		try {
		
		if(universityCode.equals("A"))
		{
			Message<University> university= dao1.findUniversity();
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					int  studentAmount = dao.amountStudentForOverall(university.getList().get(i).getUniversityCode(),attendantCode,startDate,endDate);
					int  DiagnosisAmount = dao.amountDianosisForOverall(university.getList().get(i).getUniversityCode(),attendantCode,startDate,endDate);
					int  level1 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 100 ,attendantCode,startDate,endDate);
					int  level2 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 80 ,attendantCode,startDate,endDate);
					int  level3 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 60 ,attendantCode,startDate,endDate);
					int  level4 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 40 ,attendantCode,startDate,endDate);
					int  level5 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 20 ,attendantCode,startDate,endDate);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					resultSub.put("amountStudents", studentAmount);
					resultSub.put("amountDisgnosis", DiagnosisAmount);
					resultSub.put("level1", level1);
					resultSub.put("level2", level2);
					resultSub.put("level3", level3);
					resultSub.put("level4", level4);
					resultSub.put("level5", level5);
					resultSub.put("sumlevel", level5+level4+level3+level2+level1);
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "Confidence Reprot ok");
				result.put("ConfidenceReport", n);
			}
			
		}
		else
		{
			Message<University> university= dao1.findUniversity(universityCode);
			
			if(university != null && university.getList().size() > 0){
				for (int i = 0; i < university.getList().size(); i++) {
					int  studentAmount = dao.amountStudentForOverall(university.getList().get(i).getUniversityCode(),attendantCode,startDate,endDate);
					int  DiagnosisAmount = dao.amountDianosisForOverall(university.getList().get(i).getUniversityCode(),attendantCode,startDate,endDate);
					int  level1 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 100 ,attendantCode,startDate,endDate);
					int  level2 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 80 ,attendantCode,startDate,endDate);
					int  level3 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 60 ,attendantCode,startDate,endDate);
					int  level4 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 40 ,attendantCode,startDate,endDate);
					int  level5 = dao.amountConfidenceScore(university.getList().get(i).getUniversityCode(), 20 ,attendantCode,startDate,endDate);
					resultSub = new JSONObject();
					resultSub.put("universityName", university.getList().get(i).getUniversityName());
					resultSub.put("amountStudents", studentAmount);
					resultSub.put("amountDisgnosis", DiagnosisAmount);
					resultSub.put("level1", level1);
					resultSub.put("level2", level2);
					resultSub.put("level3", level3);
					resultSub.put("level4", level4);
					resultSub.put("level5", level5);
					resultSub.put("sumlevel", level5+level4+level3+level2+level1);
					n.put(resultSub);
					
				}
				
				result.put("statusCode", "0");
				result.put("statusMsg", "Confidence Reprot ok");
				result.put("ConfidenceReport", n);
			}
		}
		/*attendant.setAttendantPassword(attendantPassword);
		Message<Attendant> attendantList = null;
		
			attendantList = dao.checkLogin(attendant);
			result.put("statusCode", attendantList.getStatusCode());
			result.put("statusMsg", attendantList.getStatusMsg());
			result.put("attendant", attendantList.getList());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", "-1");
			result.put("statusMsg", e.getMessage());
		}
		
		

		return Response.status(200).entity(result.toString()).build();
	}
}
