package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.portfolio.model.Message;
import com.portfolio.model.Student;
import com.portfolio.util.ConnectionHelper;



public class StudentDao {
	public Message<Student> insertStudent(Student student) throws Exception {
		
		System.out.println("on method insertStudent() of Student table");
		Connection conn = null;
		Message<Student> message = new Message<Student>();
		try {
			String sqlInsert = "insert into student (student_code, student_email, student_password , "
					+ " student_first_name, student_last_name, student_university,apprentice_startdate ,apprentice_enddate) "
					+ " values (?,?,?,?,?,?,?,?)";
			PreparedStatement insert;
			conn = ConnectionHelper.getConnection();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStartFormat = formatter.parse(student.getApprenticeStartDate());
			Date dateEndFormat = formatter.parse(student.getApprenticeEndDate());
			java.sql.Date startDate = new java.sql.Date(dateStartFormat.getTime());
			java.sql.Date endDate = new java.sql.Date(dateEndFormat.getTime());
			
			insert = conn.prepareStatement(sqlInsert);
			insert.setString(1, student.getStudentCode());
			insert.setString(2, student.getStudentEmail());
			insert.setString(3, student.getStudentPassword());
			insert.setString(4, student.getStudentFirstName());
			insert.setString(5, student.getStudentLastName());
			insert.setString(6, student.getStudentUniversity());
			insert.setDate(7, startDate);
			insert.setDate(8, endDate);
	
			insert.executeUpdate();

			int rows = insert.getUpdateCount();
			System.out.println("rows = " + rows);
			if (rows == 0) {
				message.setStatusCode("insertStudent Incorrect");
				message.setStatusMsg("1");
			} else {
				message.setStatusMsg("insertStudent Completed");
				message.setStatusCode("0");
				System.out.println("=================== End insertStudent Complete  =====================");

			}
			insert.close();
		} catch (Exception e) {
			e.printStackTrace();
			String tableKey = e.getMessage();
			throw new Exception(
					"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
		}
		conn.close();
		return message;
	}

	
public Message<Student> checkLogin(Student student) throws Exception {
		
		System.out.println("on method checkLogin() of Student table");
		Connection conn = null;
		ResultSet rs ;
		PreparedStatement stm ;
		Message<Student> message = new Message<Student>();
		try {		
			String sql = "select *  "+
				" from student  where (? is null OR (student_code = ?)) AND (? is null OR (student_email = ?)) and student_password = ?  ";
			conn = ConnectionHelper.getConnection();
			stm = conn.prepareStatement(sql);
			if(!("").equals(student.getStudentCode()))
			{
				stm.setString(1, student.getStudentCode());
				stm.setString(2, student.getStudentCode());
				stm.setNull(3, java.sql.Types.NULL);
				stm.setNull(4, java.sql.Types.NULL);
				stm.setString(5, student.getStudentPassword());
			}
			else if(!("").equals(student.getStudentEmail()))
			{
				stm.setNull(1, java.sql.Types.NULL);
				stm.setNull(2, java.sql.Types.NULL);
				stm.setString(3, student.getStudentEmail());
				stm.setString(4, student.getStudentEmail());
				stm.setString(5, student.getStudentPassword());
				
			}
			
			rs  = stm.executeQuery();
			Student stud = new Student();
			List<Student> studentlist = new ArrayList<Student>();
			if (rs.next())
			{
				stud.setStudentId(rs.getInt("student_id"));
				stud.setStudentCode(rs.getString("student_code"));
				stud.setStudentEmail(rs.getString("student_email"));
				stud.setStudentPassword(rs.getString("student_password"));
				stud.setStudentFirstName(rs.getString("student_first_name"));
				stud.setStudentLastName(rs.getString("student_last_name"));
				message.setStatusCode("0");
				message.setStatusMsg("login complete");
				studentlist.add(stud);
				System.err.println(rs.getString("student_password"));
				message.setList(studentlist);
			}
			else
			{
				message.setStatusCode("1");
				message.setStatusMsg("login incorrect");
			}
			
			
			
				//System.out.println("haveRow>>> false");
			conn.close();
			rs.close();
			

		} catch (Exception e) {
			e.printStackTrace();
			String tableKey = e.getMessage();
			throw new Exception(
					"ERROR:: " + tableKey + "\nCaught: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
		}
		conn.close();
		return message;
	}



	public static void main(String[] args) {
		Student stu = new Student();
		stu.setStudentCode("590001");
		stu.setStudentEmail("tongmaster@gmail.com");
		stu.setStudentPassword("44pps");
		StudentDao methodStud = new StudentDao();
		try {
			methodStud.insertStudent(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("hello");
			e.printStackTrace();
		}
	}
}
