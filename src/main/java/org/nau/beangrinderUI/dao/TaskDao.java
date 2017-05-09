package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Task;


public class TaskDao {
	
	Database database;
	 Connection connection;
	
	public TaskDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public ArrayList<Task> getTaskDetails(String activityId) throws Exception
	{
		ArrayList<Task> taskList = new ArrayList<Task>();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM tasks where activity_id= ?");
			ps.setString(1,activityId);
			ResultSet rs = ps.executeQuery();
			
				
			while(rs.next())
			{
						Task task = new Task();
						task.setTaskId(rs.getString("task_id"));
						task.setActivityId(rs.getString("activity_id"));
						task.setTaskName(rs.getString("task_name"));
						task.setTaskStatement(rs.getString("task_statement"));
						task.setEndDate(rs.getString("task_end_date"));
						task.setStartDate(rs.getString("task_start_date"));
						
						taskList.add(task);
			}
			
			return taskList;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
}
