package sg.edu.iss.team5.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.repositories.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseRepo courseRepository;

	@Override
	@Transactional
	public ArrayList<Course> findAllCourses() {
		ArrayList<Course> l = (ArrayList<Course>) courseRepository.findAll();
		return l;
	}
	
	public ArrayList<Course> findAllLecturerCourses(Lecturer lecturer) {
		ArrayList<Course> l = (ArrayList<Course>) courseRepository.findAllByLecturers(lecturer);
		return l;
	}
	
	@Transactional
	public Course findCourse(String id) {
		return courseRepository.findById(id).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Course createCourse(Course course) {
		return courseRepository.saveAndFlush(course);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Course changeCourse(Course course) {
		return courseRepository.saveAndFlush(course);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeCourse(Course course) {
		courseRepository.delete(course);
	}

}
