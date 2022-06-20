package sg.edu.iss.team5.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;

public interface StudentRepo extends JpaRepository<Student, String>{

	ArrayList<Student_Course> findAllBystudentID(Student student);
}
