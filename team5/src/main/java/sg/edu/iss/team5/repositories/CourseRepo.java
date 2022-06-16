package sg.edu.iss.team5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team5.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
