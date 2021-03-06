package com.portfolio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



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
			//String url = "jdbc:mysql://localhost:3306/smartportfolio?user=smartportfolio&password=smartportfolio209&useEncoding=true&characterEncoding=UTF-8";
			String url = "jdbc:mysql://localhost:3306/PortFolio?user=root&password=&useEncoding=true&characterEncoding=UTF-8";
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
	
	public static void main(String[] args) {
		getConnection();
	}
	
	
	
	


}