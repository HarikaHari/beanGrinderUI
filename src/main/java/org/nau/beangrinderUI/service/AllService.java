package org.nau.beangrinderUI.service;

import java.util.List;

import org.nau.beangrinderUI.model.*;



public interface AllService {
	

	String findStudentById(Student obj) throws Exception;

	List<Course> fetchAllCourses(String studentId) throws Exception;

	List<Activity> fetchAllActivities(String courseId);

	List<Task> fetchAllTasks(String activityId);
	
}
