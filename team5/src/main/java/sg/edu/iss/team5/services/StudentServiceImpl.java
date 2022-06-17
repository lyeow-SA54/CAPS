package sg.edu.iss.team5.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.helper.role;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentRepo studentRepository;

	@Override
	@Transactional
	public ArrayList<Student> findAllStudents() {
		ArrayList<Student> l = (ArrayList<Student>) studentRepository.findAll();
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
		student.setUser(new User(student.getStudentID(), role.STUDENT));
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
	}

}
