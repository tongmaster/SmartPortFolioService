package com.portfolio.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.dao.DeadLineDao;
import com.portfolio.dao.DiagnosisDao;
import com.portfolio.dao.StudentDao;
import com.portfolio.model.Diagnosis;
import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;

@Path("/diagnosis")
public class DiagnosisEndPoint {

	
	/*
	@Path("/patient/")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response DiagnosisPatient(Diagnosis diagnosis) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(diagnosis.getPatientFirstName()+" "+diagnosis.getSymptoms()+" "+diagnosis.getTreatment()+" "+diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		
		DiagnosisDao dao = new  DiagnosisDao();
		StudentDao dao1 = new  StudentDao();
		DeadLineDao dao2 = new  DeadLineDao();
		Message<Diagnosis> diagList = null;
		try {
			Student appren = dao1.getStudent(diagnosis.getStudentCode());
			if(appren != null)
			{
				Deadline dead = dao2.findDeadLine(diagnosis.getAttendantCode());
				if(dead != null)
				{
					
					
					Calendar calendar = new GregorianCalendar( remember about timezone! );
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(appren.getApprenticeStartDate());
				    Date parsedDate = dateFormat.parse(appren.getApprenticeStartDate());
					calendar.setTime(parsedDate);
					calendar.add(Calendar.DATE, Integer.parseInt(dead.getDeadline1()));
					
					Date report = dateFormat.parse(diagnosis.getDiagnosisDate());
					System.out.println(report +" {{}} "+calendar.getTime() );
					if(report.before(calendar.getTime()))
					{
						diagnosis.setDeadlineDate(dateFormat.format(calendar.getTime()));
						diagnosis.setStatus("N");
						diagList = dao.insertDiagnosis(diagnosis);
						jsonObject.put("statusCode", diagList.getStatusCode());
						jsonObject.put("statusMsg", diagList.getStatusMsg());
						jsonObject.put("diagnosis", diagList.getList());
					}
					else
					{
						diagnosis.setDeadlineDate(dateFormat.format(calendar.getTime()));
						diagnosis.setStatus("D");
						diagList = dao.insertDiagnosis(diagnosis);
						jsonObject.put("statusCode", diagList.getStatusCode());
						jsonObject.put("statusMsg", diagList.getStatusMsg());
						jsonObject.put("diagnosis", diagList.getList());
				
					}
					
					
					
				}
				else
				{
					jsonObject.put("statusCode", "1");
					jsonObject.put("statusMsg", "deadline not found");
				}
				
			}
			else
			{
				jsonObject.put("statusCode", "2");
				jsonObject.put("statusMsg", "Apprentice not found");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	*/
	
	@Path("/patient/")
	@POST
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response DiagnosisPatient(Diagnosis diagnosis) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		System.out.println(diagnosis.getPatientFirstName()+" "+diagnosis.getSymptoms()+" "+diagnosis.getTreatment()+" "+diagnosis.getDiagnosisDate()+" "+diagnosis.getDiagnosisTime());
		
		DiagnosisDao dao = new  DiagnosisDao();
		StudentDao dao1 = new  StudentDao();
		DeadLineDao dao2 = new  DeadLineDao();
		Message<Diagnosis> diagList = null;
		try {
			Student appren = dao1.getStudent(diagnosis.getStudentCode());
			if(appren != null)
			{
				//SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "EN"));
				//TimeZone tz = TimeZone.getDefault();
				//sdf.setTimeZone(TimeZone.getDefault());
				//System.out.println(tz.getDisplayName());
				//diagnosis.setDiagnosisDate("2016-07-11");
				System.out.println("apprenTice "+appren.getApprenticeEndDate());
				System.out.println("diagnosisDate  "+diagnosis.getDiagnosisDate());
				String a = appren.getApprenticeEndDate().substring(0, 4);
				String b = (Integer.parseInt(a)-543)+"";
				//String b = (Integer.parseInt(a))+"";
				String c = appren.getApprenticeEndDate().substring(4);
	        	//Date date1 = sdf.parse(date1String);
	        	//Timestamp date2 = ConnectionHelper.getDateSql2(appren.getApprenticeEndDate());
	        	//Date date1 = ConnectionHelper.getCurrentDateSql();
	        	//Date date1 = ConnectionHelper.getDateSql(diagnosis.getDiagnosisDate());
				//Date a = sdf.parse(diagnosis.getDiagnosisDate());
				//Date date1 = ConnectionHelper.getDate(diagnosis.getDiagnosisDate());
				//diagnosis.setDiagnosisDate(sdf.format(date1));
				Date dateEndFormat = formatter.parse(diagnosis.getDiagnosisDate());
				Date dateEndFormat2 = formatter.parse(b+c);
				java.sql.Date date1 = new java.sql.Date(dateEndFormat.getTime());
				System.out.println("date 1 >>"+date1+"  "+dateEndFormat);
				//System.out.println(date1+"  "+dateEndFormat);
				Date date2 = ConnectionHelper.getDate(appren.getApprenticeEndDate());
				System.out.println("date 1 >>"+date2+"  "+dateEndFormat2);
	        	System.out.println(date1+"  "+date2);
	        	
	        	if(date1.compareTo(date2)<=0)
				{
	        		System.out.println("less than");
					diagnosis.setDeadlineDate(appren.getApprenticeEndDate());
					diagnosis.setStatus("N");
					diagList = dao.insertDiagnosis(diagnosis);
					jsonObject.put("statusCode", diagList.getStatusCode());
					jsonObject.put("statusMsg", diagList.getStatusMsg());
					jsonObject.put("diagnosis", diagList.getList());
				}
				else
				{
					System.out.println("greater  than");
					diagnosis.setDeadlineDate(appren.getApprenticeEndDate());
					diagnosis.setStatus("D");
					diagList = dao.insertDiagnosis(diagnosis);
					jsonObject.put("statusCode", diagList.getStatusCode());
					jsonObject.put("statusMsg", diagList.getStatusMsg());
					jsonObject.put("diagnosis", diagList.getList());
			
				}
	        	
					/*
					Calendar calendar = new GregorianCalendar( remember about timezone! );
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(appren.getApprenticeStartDate());
				    Date parsedDate = dateFormat.parse(appren.getApprenticeStartDate());
					calendar.setTime(parsedDate);
					calendar.add(Calendar.DATE, Integer.parseInt(dead.getDeadline1()));
					
					Date report = dateFormat.parse(diagnosis.getDiagnosisDate());
					System.out.println(report +" {{}} "+calendar.getTime() );
					if(report.before(calendar.getTime()))
					{
						diagnosis.setDeadlineDate(dateFormat.format(calendar.getTime()));
						diagnosis.setStatus("N");
						diagList = dao.insertDiagnosis(diagnosis);
						jsonObject.put("statusCode", diagList.getStatusCode());
						jsonObject.put("statusMsg", diagList.getStatusMsg());
						jsonObject.put("diagnosis", diagList.getList());
					}
					else
					{
						diagnosis.setDeadlineDate(dateFormat.format(calendar.getTime()));
						diagnosis.setStatus("D");
						diagList = dao.insertDiagnosis(diagnosis);
						jsonObject.put("statusCode", diagList.getStatusCode());
						jsonObject.put("statusMsg", diagList.getStatusMsg());
						jsonObject.put("diagnosis", diagList.getList());
				
					}*/
			}
			else
			{
				jsonObject.put("statusCode", "2");
				jsonObject.put("statusMsg", "Apprentice not found");
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("statusCode", "-1");
			jsonObject.put("statusMsg", e.getMessage());
		}
		
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	
	 public static void main( String[] args ) 
	    {
	    	DiagnosisEndPoint a = new DiagnosisEndPoint();
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setStudentCode("182736");
			
			a.DiagnosisPatient(diagnosis);
			
		
		/*	try {
				 SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

					String dateInString = "22-01-2015 10:15:55";
					Date date;
				date = formatter.parse(dateInString);
				TimeZone tz = TimeZone.getDefault();

				// From TimeZone Asia/Singapore
				System.out.println("TimeZone : " + tz.getID() + " - " + tz.getDisplayName());
				System.out.println("TimeZone : " + tz);
				System.out.println("Date : " + formatter.format(date));

				// To TimeZone America/New_York
				SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
				TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Bangkok");
				sdfAmerica.setTimeZone(tzInAmerica);
				
				String sDateInAmerica = sdfAmerica.format(date); // Convert to String first
				Date dateInAmerica = formatter.parse(sDateInAmerica);

				System.out.println("\nTimeZone : " + tzInAmerica.getID() + 
		                                      " - " + tzInAmerica.getDisplayName());
				System.out.println("TimeZone : " + tzInAmerica);
				System.out.println("Date (String) : " + sDateInAmerica);
				System.out.println("Date (Object) : " + formatter.format(dateInAmerica)); 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
	    }

}
