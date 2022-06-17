package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Course;

public interface CourseService {

	ArrayList<Course> findAllCourses();
	
	Course findCourse(String id);

	Course createCourse(Course course);

	Course changeCourse(Course course);

	void removeCourse(Course course);	

}
