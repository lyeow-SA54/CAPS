package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.repositories.CourseRepo;
import sg.edu.iss.team5.repositories.EnrolmentRepo;
import sg.edu.iss.team5.repositories.LecturerRepo;
import sg.edu.iss.team5.repositories.StudentRepo;


@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseRepo courseRepository;

	
	@Resource
	private EnrolmentRepo enrollRepository;
	
	@Resource
	private StudentRepo studentRepository;

	@Resource
	private LecturerRepo lecturerRepository;
	@Autowired
	private LecturerService lService;


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
		return courseRepository.saveAndFlush(ogCourse);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeCourse(Course course) {
		ArrayList<Student_Course> enrollment = enrollRepository.findAllByCourseID(course);
		for(Student_Course e : enrollment) {
		e.getStudentID().getStudyList().remove(e);
		studentRepository.save(e.getStudentID());
		};
		
		Set<Lecturer> lecturers = course.getLecturers();
		for(Lecturer l : lecturers) {
		l.getTeachings().remove(course);
		lecturerRepository.save(l);
		};
		course.getLecturers().clear();
		courseRepository.delete(course);
		courseRepository.flush();
	}
	@Override
	@Transactional
	public Course assignCourseToLecturer(String courseId, String lecturerId) {
		// TODO Auto-generated method stub	
		
		
		Lecturer lecturer = lService.findLecturer(lecturerId);
		Course course = this.findCourse(courseId);
		
		Set<Course> teachList =  lecturer.getTeachings();
		teachList.add(course);
		lecturer.setTeachings(teachList);
		
		lService.changeLecturer(lecturer);	
		course.getLecturers().add(lecturer);
		return courseRepository.saveAndFlush(course);
	}
	
	@Override
	@Transactional
	public Course removeLectureFromCourse(String courseId, String lecturerId) {
		// TODO Auto-generated method stub	
		
		
		Lecturer lecturer = lService.findLecturer(lecturerId);
		Course course = this.findCourse(courseId);
		
		Set<Course> teachList =  lecturer.getTeachings();
		teachList.remove(course);
			
		lService.changeLecturer(lecturer);	
		course.getLecturers().remove(lecturer);
		return courseRepository.saveAndFlush(course);
	}
	

}
