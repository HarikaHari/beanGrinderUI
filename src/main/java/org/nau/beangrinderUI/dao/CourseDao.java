package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Course;


public class CourseDao {
	
	Database database;
	 Connection connection;
	
	public CourseDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Course> getCourseDetails(String studentId) throws Exception
	{
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<String> courseIdList = new ArrayList<String>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM course");
			PreparedStatement psCourse = 
					connection.prepareStatement("SELECT * FROM student_course where Student_Id = ?");
			psCourse.setString(1,studentId);
			ResultSet rs = ps.executeQuery();
			ResultSet rsCourse = psCourse.executeQuery();
			
			while(rsCourse.next())
				courseIdList.add(rsCourse.getString("Course_Id"));
				
			while(rs.next())
			{
				if(courseIdList.contains(rs.getString("Course_id"))) {
						Course course = new Course();
						course.setCourseId(rs.getString("Course_id"));
						course.setCourseName(rs.getString("Course_Name"));
						course.setStartDate(rs.getString("Course_Start_Date"));
						course.setEndDate(rs.getString("Course_End_Date"));
						course.setInstructorId(rs.getString("Instructor_Id"));
						
						courseList.add(course);
				}
				
			}
			
			return courseList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
}
