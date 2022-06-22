package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.repositories.CourseRepo;
import sg.edu.iss.team5.repositories.EnrolmentRepo;
import sg.edu.iss.team5.repositories.LecturerRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseRepo courseRepository;
	
	@Resource
	private EnrolmentRepo enrolRepository;
	
	@Resource
	private LecturerRepo lecturerRepository;

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
		Course ogCourse = findCourse(course.getCourseID());
//		Set<Lecturer> lecturers = ogCourse.getLecturers();
//		lecturers.forEach(l -> {
//			l.removeCourse(ogCourse);
//		});
		ogCourse.setClassPax(course.getClassPax());
		ogCourse.setCourseDays(course.getCourseDays());
		ogCourse.setDescription(course.getDescription());
		ogCourse.setLessonDay(course.getLessonDay());
		ogCourse.setName(course.getName());
		ogCourse.setStartDate(course.getStartDate());
		ogCourse.setCode(course.getCode());
		ogCourse.setCredits(course.getCredits());
		ogCourse.setFee(course.getFee());
		ogCourse.setMaxCap(course.getMaxCap());
		ogCourse.setRoom(course.getRoom());
		
//		ogCourse.setClassPax(course.getClassPax());
//		ogCourse.setCourseDays(course.getCourseDays());
//		ogCourse.setDescription(course.getDescription());
//		ogCourse.setLessonDay(course.getLessonDay());
//		ogCourse.setName(course.getName());
//		ogCourse.setStartDate(course.getStartDate());
//		ogCourse.setCode(course.getCode());
//		ogCourse.setCredits(course.getCredits());
//		ogCourse.setFee(course.getFee());
//		ogCourse.setMaxCap(course.getMaxCap());
//		ogCourse.setRoom(course.getRoom());
//		lecturers.forEach(l -> {
//			l.addCourse(ogCourse);
//			lecturerRepository.save(l);
//		});
		return courseRepository.saveAndFlush(ogCourse);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeCourse(Course course) {
		ArrayList<Student_Course> enrollment = enrolRepository.findAllByCourseID(course);
//		if (enrollment!=null)
//		{
//		enrollment.forEach(e -> enrolRepository.delete(e));
//		}
		ArrayList<Lecturer> lecturers = lecturerRepository.findAllByTeachings(course);
		lecturers.forEach(l -> 
		{
			course.removeLecturer(l);
		});
		courseRepository.delete(course);
	}

}
