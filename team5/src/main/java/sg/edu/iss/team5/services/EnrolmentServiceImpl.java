package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public Student_Course findEnrolment(String id) {
		return enrollRepository.findById(id).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student_Course createEnrolment(Student_Course enroll) {
//		Student s = sService.findStudent(enroll.getStudentID().getStudentID());
//		Course c = cService.findCourse(enroll.getCourseID().getCourseID());
//		enroll.setStudentID(s);
//		enroll.setCourseID(c);
//		Set<Student_Course> slist = s.getStudyList();
//		slist.add(enroll);
//		s.setStudyList(slist);
//		sService.changeStudent(s);
//		List<Student_Course> clist = c.getClassList();
//		clist.add(enroll);
//		c.setClassList(clist);
//		cService.changeCourse(c);
		return enrollRepository.saveAndFlush(enroll);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student_Course changeEnrolment(Student_Course enroll) {
		return enrollRepository.saveAndFlush(enroll);
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
