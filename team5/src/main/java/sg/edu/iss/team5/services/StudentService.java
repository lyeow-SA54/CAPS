package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Student;

public interface StudentService {

	ArrayList<Student> findAllStudents();
	
	Student findStudent(String id);

	Student createStudent(Student student);

	Student changeStudent(Student student);

	void removeStudent(Student student);	

}
