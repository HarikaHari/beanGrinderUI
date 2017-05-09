package org.nau.beangrinderUI.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.nau.beangrinderUI.dao.ActivityDao;
import org.nau.beangrinderUI.dao.CourseDao;
import org.nau.beangrinderUI.dao.Database;
import org.nau.beangrinderUI.dao.StudentDao;
import org.nau.beangrinderUI.dao.TaskDao;
import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.Student;
import org.nau.beangrinderUI.model.Task;

@Service("allService")
public class AllServiceImpl implements AllService{
    /*@Autowired
    StudentDao studentDao; 
	*/
	@Autowired
	HttpSession httpSession;
	
	StudentDao studentDao = new StudentDao();
	CourseDao courseDao = new CourseDao();
	ActivityDao activityDao = new ActivityDao();
	TaskDao taskDao = new TaskDao();

	@Override
	public String findStudentById(Student obj) {
		Student student = null;
		try {
			student =studentDao.getStudentDetails(obj.getStudentId(), obj.getPassword());
			return student.getStudentId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Course> fetchAllCourses(String studentId) throws Exception {
		List<Course> courseList = new ArrayList<Course>(); 
		try {
			courseList = courseDao.getCourseDetails(studentId);
			return courseList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Activity> fetchAllActivities(String courseId){
		List<Activity> activityList = new ArrayList<Activity>(); 
		try {
			activityList = activityDao.getActivityDetails(courseId);
			return activityList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Task> fetchAllTasks(String activityId){
		List<Task> taskList = new ArrayList<Task>(); 
		try {
			taskList = taskDao.getTaskDetails(activityId);
			return taskList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
