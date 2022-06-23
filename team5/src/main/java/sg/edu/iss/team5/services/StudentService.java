package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;

public interface StudentService {

	ArrayList<Student> findAllStudents();
	
	ArrayList<Student_Course> findAllCoursesByStudent(Student student);
	
	Student findStudent(String id);

	Student createStudent(Student student);

	Student changeStudent(Student student);

	void removeStudent(Student student);	

	ArrayList<Student> findPendingCoursesByStudent(String sid);	
}
