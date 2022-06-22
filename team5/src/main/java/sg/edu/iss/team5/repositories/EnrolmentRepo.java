package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;

public interface EnrolmentRepo extends JpaRepository<Student_Course, String>{
	@Query("SELECT DISTINCT sc.sc_ID FROM Student_Course sc")
	ArrayList<String> findAllStudent_CourseIDs();
	
	ArrayList<Student_Course> findAllByCourseID(Course course);
	
	ArrayList<Student_Course> findAllByStudentID(Student student);
	
	Student_Course findByCourseIDAndStudentID(Course course, Student student);
	
	ArrayList<Student_Course> findAllByEventTypeAndStudentID(status status, Student student);
}
