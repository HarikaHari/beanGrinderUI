package org.nau.beangrinderUI.controller;
 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nau.beangrinderUI.model.Activity;
import org.nau.beangrinderUI.model.Course;
import org.nau.beangrinderUI.model.Student;
import org.nau.beangrinderUI.model.Task;
import org.nau.beangrinderUI.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
@RestController
public class AllController {
 
    @Autowired
    AllService serviceObj;  
    
    @Autowired 
    private HttpSession httpSession;
 
    
    
    //-------------------Retrieve Single Student--------------------------------------------------------
     
    @RequestMapping(value = "/student/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@RequestBody Student obj) {
        String id = null;
		try {
			id = serviceObj.findStudentById(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (id == null) {
            System.out.println("User with id " + obj.getStudentId() + " not found");
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        httpSession.setAttribute("studentId", id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
 
    //-------------------Retrieve courses --------------------------------------------------------
    
    @RequestMapping(value = "/course/getAllCourses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> listAllCourses() {
        List<Course> Course = new ArrayList<Course>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			Course = serviceObj.fetchAllCourses(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(Course.isEmpty()){
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Course>>(Course, HttpStatus.OK);
    }

    
    //-------------------Retrieve Activities --------------------------------------------------------
    
    @RequestMapping(value = "/activity/getAllActivities", method = RequestMethod.POST)
    public ResponseEntity<List<Activity>> listAllActivites(@RequestBody String courseId) {
        List<Activity> activityList = new ArrayList<Activity>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			activityList = serviceObj.fetchAllActivities(courseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(activityList.isEmpty()){
            return new ResponseEntity<List<Activity>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Activity>>(activityList, HttpStatus.OK);
    }
    
    //-------------------Retrieve Tasks --------------------------------------------------------
    
    @RequestMapping(value = "/tasks/getAllTasks", method = RequestMethod.POST)
    public ResponseEntity<List<Task>> listAllTasks(@RequestBody String activityId) {
        List<Task> taskList = new ArrayList<Task>();
		try {
			String studentId = (String)httpSession.getAttribute("studentId");
			taskList = serviceObj.fetchAllTasks(activityId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(taskList.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
    }
 
}