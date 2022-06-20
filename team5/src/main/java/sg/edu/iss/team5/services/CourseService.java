package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;

public interface CourseService {

	ArrayList<Course> findAllCourses();
	
	ArrayList<Course> findAllLecturerCourses(Lecturer lecturer);
	
	Course findCourse(String id);

	Course createCourse(Course course);

	Course changeCourse(Course course);

	void removeCourse(Course course);	

}
