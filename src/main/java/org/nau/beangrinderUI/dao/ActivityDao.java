package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Activity;


public class ActivityDao {
	
	Database database;
	 Connection connection;
	
	public ActivityDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Activity> getActivityDetails(String courseId) throws Exception
	{
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM activities where courseId= ?");
			ps.setString(1,courseId);
			ResultSet rs = ps.executeQuery();
			
				
			while(rs.next())
			{
						Activity activity = new Activity();
						activity.setCourseId(rs.getString("CourseId"));
						activity.setActivityId(rs.getString("Activity_Id"));
						activity.setActivityName(rs.getString("Activity_Name"));
						activity.setActivityDetails(rs.getString("Activity_Details"));
						activity.setEndDate(rs.getString("Activity_End_Date"));
						activity.setStartDate(rs.getString("Activity_Start_Date"));
						
						activityList.add(activity);
			}
			
			return activityList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
}
