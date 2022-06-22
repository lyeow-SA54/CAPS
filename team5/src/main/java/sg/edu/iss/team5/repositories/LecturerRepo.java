package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;

public interface LecturerRepo extends JpaRepository<Lecturer, String>{
	
	ArrayList<Lecturer> findAllByTeachings(Course course);

}