package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.nau.beangrinderUI.model.Student;


public class StudentDao {
	Database database;
	 Connection connection;
	
	 public StudentDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public Student getStudentDetails(String id, String pass) throws Exception
	{
		Student student = new Student();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM student where student_id = ? and password = ?");
			ps.setString(1,id);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				student.setStudentId(rs.getString("student_id"));
				student.setPassword(rs.getString("password"));
				student.setEmail(rs.getString("email"));
				student.setStudentName(rs.getString("student_name"));
				student.setDepartment(rs.getString("student_dept"));
			}
			return student;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
}
