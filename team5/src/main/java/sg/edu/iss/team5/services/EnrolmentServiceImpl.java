package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.repositories.EnrolmentRepo;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {

	@Resource
	private EnrolmentRepo enrollRepository;
	@Autowired
	private StudentService sService;
	@Autowired
	private CourseService cService;

	@Override
	@Transactional
	public ArrayList<Student_Course> findAllEnrolment() {
		ArrayList<Student_Course> l = (ArrayList<Student_Course>) enrollRepository.findAll();
		return l;
	}
	
	@Override
	@Transactional
	public ArrayList<Student_Course> findAllEnrolmentByCourse(Course course) {
		ArrayList<Student_Course> l = (ArrayList<Student_Course>) enrollRepository.findAllByCourseID(course);
		return l;
	}
	
	@Override
	@Transactional
	public ArrayList<Student_Course> findAllEnrolmentByStudent(Student student) {
		ArrayList<Student_Course> l = (ArrayList<Student_Course>) enrollRepository.findAllByStudentID(student);
		return l;
	}
	
	@Transactional
	public Student_Course findEnrolment(String id) {
		return enrollRepository.findById(id).orElse(null);

	}
	
	@Transactional
	public Student_Course findEnrolmentByCourseAndStudent(Course course, Student student) {
		return enrollRepository.findByCourseIDAndStudentID(course, student);

	}
	
	@Transactional
	public ArrayList<Course> findAvailableEnrolmentByStudent(Student student) {
		ArrayList<Student_Course> gradEnrollList = enrollRepository.findAllByEventTypeAndStudentID(status.GRADUATED, student);
		ArrayList<Student_Course> progressEnrollList = enrollRepository.findAllByEventTypeAndStudentID(status.INPROGRESS, student);
		ArrayList<Student_Course> submittedEnrollList = enrollRepository.findAllByEventTypeAndStudentID(status.SUBMITTED, student);
		ArrayList<Student_Course> excludeEnrollList = new ArrayList<Student_Course>();
		excludeEnrollList.addAll(gradEnrollList);
		excludeEnrollList.addAll(progressEnrollList);
		excludeEnrollList.addAll(submittedEnrollList);
		Set<String> excludeCourseIDList = new HashSet<String>();
		excludeEnrollList.forEach(sc -> excludeCourseIDList.add(sc.getCourseID().getCourseID()));
		ArrayList<Course> courseList =  cService.findAllCourses();
		ArrayList<Course> availcourseList =  new ArrayList<Course>();
		courseList.forEach(c -> 
		{
			if (excludeCourseIDList.contains(c.getCourseID()))
		{}
			else
		{availcourseList.add(c);}
		});
		return availcourseList;
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student_Course createEnrolment(Student_Course stu_c) {
		String sc_id = stu_c.getStudentID().getStudentID() + "-" + stu_c.getCourseID().getCourseID();
		stu_c.setSc_ID(sc_id);
		Student s = sService.findStudent(stu_c.getStudentID().getStudentID());
		Course c = cService.findCourse(stu_c.getCourseID().getCourseID());
		Set<Student_Course> slist = s.getStudyList();
		slist.add(stu_c);
		s.setStudyList(slist);
		sService.changeStudent(s);
		Set<Student_Course> clist = c.getClassList();
		clist.add(stu_c);

		return enrollRepository.saveAndFlush(stu_c);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student_Course changeEnrolment(Student_Course stu_c) {
		Student_Course sc = findEnrolment(stu_c.getSc_ID());
		sc.setEventType(stu_c.getEventType());
		sc.setScore(stu_c.getScore());
		return enrollRepository.saveAndFlush(sc);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeEnrolment(Student_Course enroll) {
		enrollRepository.delete(enroll);
	}

}
