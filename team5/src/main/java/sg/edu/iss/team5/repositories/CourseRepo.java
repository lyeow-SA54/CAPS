package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student_Course;

public interface CourseRepo extends JpaRepository<Course, String>{

	@Query("SELECT DISTINCT c.courseID FROM Course c")
	ArrayList<String> findAllCourseIDs();
	
	ArrayList<Course> findAllByLecturers(Lecturer lecturer);
	
	
}
