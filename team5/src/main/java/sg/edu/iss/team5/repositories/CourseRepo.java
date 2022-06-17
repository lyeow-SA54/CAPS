package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.team5.model.Course;

public interface CourseRepo extends JpaRepository<Course, String>{

	@Query("SELECT DISTINCT c.courseID FROM Course c")
	ArrayList<String> findAllCourseIDs();
	
}
