package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.repositories.CourseRepo;
import sg.edu.iss.team5.repositories.LecturerRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseRepo courseRepository;
	@Resource
	private LecturerRepo lecturerRepository;
	@Autowired
	private CourseService cService;
	@Autowired
	private LecturerService lService;

	@Override
	@Transactional
	public ArrayList<Course> findAllCourses() {
		ArrayList<Course> l = (ArrayList<Course>) courseRepository.findAll();
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
	@Override
	@Transactional
	public Course assignCourseToLecturer(String courseId, String lecturerId) {
		// TODO Auto-generated method stub	
		
		
		Lecturer lecturer = lService.findLecturer(lecturerId);
		Course course = cService.findCourse(courseId);
		
		Set<Course> teachList =  lecturer.getTeachings();
		teachList.add(course);
		lecturer.setTeachings(teachList);
		
		lService.changeLecturer(lecturer);	
		course.getLecturersList().add(lecturer);
		return courseRepository.saveAndFlush(course);
	}
	
	@Override
	@Transactional
	public Course removeLectureFromCourse(String courseId, String lecturerId) {
		// TODO Auto-generated method stub	
		
		
		Lecturer lecturer = lService.findLecturer(lecturerId);
		Course course = cService.findCourse(courseId);
		
		Set<Course> teachList =  lecturer.getTeachings();
		teachList.remove(course);
			
		lService.changeLecturer(lecturer);	
		course.getLecturersList().remove(lecturer);
		return courseRepository.saveAndFlush(course);
	}

	
	
	
}
