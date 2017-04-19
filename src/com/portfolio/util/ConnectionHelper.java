package com.portfolio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;



public class ConnectionHelper
{
	private String url;
	private static ConnectionHelper instance;

	private enum NamePosition
	{
		B,D,G,H,L,N,O,P,A
	}
	private ConnectionHelper()
	{
		
    
	}

	public static Connection getConnection() {
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//production
			//String url = "jdbc:mysql://localhost:3306/smartportfolio?user=smartportfolio&password=smartportfolio209&useEncoding=true&characterEncoding=UTF-8";
			
			//uat
			//String url = "jdbc:mysql://localhost:3306/smartportfoliouat?user=smartportfolio&password=smartportfolio209&useEncoding=true&characterEncoding=UTF-8";
			
			//tong
			String url = "jdbc:mysql://localhost:3306/smartportfolio?user=root&password=&useEncoding=true&characterEncoding=UTF-8";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		getConnection();
	}*/
	
	public static Timestamp getDateSql(String datetime) {
		
	    Date parsedDate;
		try {
			Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", new Locale("th", "TH"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("th", "TH"));
			parsedDate = dateFormat.parse(datetime);
			System.out.println(">>"+datetime);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	    
		
	}
	
public static Timestamp getDateSql2(String datetime) {
		
	    Date parsedDate;
		try {
			Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", new Locale("th", "TH"));
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("th", "TH"));
			parsedDate = dateFormat.parse(datetime);
			System.out.println(">>"+datetime);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	    
		
	}
	
public static Timestamp getDateTimeSql(String datetime) {
		
	    Date parsedDate;
		try {
			Date da = new Date();
			java.sql.Date date= new java.sql.Date(da.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("th", "TH"));
			parsedDate = dateFormat.parse(datetime);
			System.out.println(">>"+parsedDate);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	    
		
	}


public static Date getCurrentDateSql() {
	
    Date parsedDate;
	Date da = new Date();
	java.sql.Date date= new java.sql.Date(da.getTime());
	Timestamp timestamp = new java.sql.Timestamp(date.getTime());
	return date;
    
	
}

public static Date getDate(String dateString)
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date dateEndFormat = null;
	try {
		dateEndFormat = formatter.parse(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	java.sql.Date startDate = new java.sql.Date(dateEndFormat.getTime());
	return startDate;
}



public static Timestamp getTimestamp(String dateString)
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date dateEndFormat = null;
	try {
		dateEndFormat = formatter.parse(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	java.sql.Timestamp startDate = new java.sql.Timestamp(dateEndFormat.getTime());
	return startDate;
}
	

public static List getBetweenMonthForYear(int monthStart,int monthEnd, int year) {
	
    /*
	Calendar startCalendar = new GregorianCalendar();
	startCalendar.setTime(startDate);
	Calendar endCalendar = new GregorianCalendar();
	endCalendar.setTime(endDate);

	int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
	int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	*/
	
	List a = new ArrayList();
	
	for (int i = monthStart; i <= monthEnd; i++) {
		//System.out.println(i+"/"+year);
		a.add(i+"/"+(year+543));
	}
	return a;
	
}
public static void main(String[] args) {
	List a = getBetweenMonthForYear(1, 7, 2016);
	System.out.println(a);
	
}

}