package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;

public interface StudentRepo extends JpaRepository<Student, String>{

	ArrayList<Student_Course> findAllBystudentID(Student student);
	
	@Query("SELECT s from Student s WHERE s.studentID = :sid AND (s.comment != null)AND (s.eventType ='APPROVED' OR s.eventType ='REJECTED')")
	ArrayList<Student> findPendingCoursesBySID(String sid);
}
