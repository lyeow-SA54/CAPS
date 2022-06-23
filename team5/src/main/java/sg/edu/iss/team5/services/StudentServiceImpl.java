package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.model.Role;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.RoleRepo;
import sg.edu.iss.team5.repositories.StudentRepo;
import sg.edu.iss.team5.repositories.UserRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentRepo studentRepository;
	
	@Resource
	private UserRepo userRepository;
	
	@Resource
	private RoleRepo roleRepository;

	@Override
	@Transactional
	public ArrayList<Student> findAllStudents() {
		ArrayList<Student> l = (ArrayList<Student>) studentRepository.findAll();
		return l;
	}
	
	@Override
	@Transactional
	public ArrayList<Student_Course> findAllCoursesByStudent(Student student) {
		ArrayList<Student_Course> l = (ArrayList<Student_Course>) studentRepository.findAllBystudentID(student);
		return l;
	}
	
	@Transactional
	public Student findStudent(String id) {
		return studentRepository.findById(id).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student createStudent(Student student) {
		Role role = new Role("STUDENT");
		List<Role> rolelist = List.of(role);
		User user  = new User(student.getStudentID(), rolelist);
		userRepository.saveAndFlush(user);
		roleRepository.saveAndFlush(role);
		student.setUser(user);
		return studentRepository.saveAndFlush(student);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Student changeStudent(Student student) {
		return studentRepository.saveAndFlush(student);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeStudent(Student student) {
		studentRepository.delete(student);
		studentRepository.flush();
	}
	@Override
	@Transactional
	public ArrayList<Student> findPendingCoursesByStudent(String sid)
	{
		return studentRepository.findPendingCoursesBySID(sid);
	}

}
