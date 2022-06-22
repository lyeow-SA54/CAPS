package sg.edu.iss.team5.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Role;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.CourseRepo;
import sg.edu.iss.team5.repositories.LecturerRepo;
import sg.edu.iss.team5.repositories.RoleRepo;
import sg.edu.iss.team5.repositories.UserRepo;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Resource
	private LecturerRepo lecturerRepository;
	
	@Resource
	private UserRepo userRepository;
	
	@Resource
	private RoleRepo roleRepository;
	
	@Resource
	private CourseRepo courseRepository;

	@Override
	@Transactional
	public ArrayList<Lecturer> findAllLecturers() {
		ArrayList<Lecturer> l = (ArrayList<Lecturer>) lecturerRepository.findAll();
		return l;
	}
	
	@Transactional
	public Lecturer findLecturer(String id) {
		return lecturerRepository.findById(id).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Lecturer createLecturer(Lecturer lecturer) {
		Role role = new Role("LECTURER");
		List<Role> rolelist = List.of(role);
		roleRepository.saveAndFlush(role);
		User user  = new User(lecturer.getLecturerID(), rolelist);
		lecturer.setUser(user);
		return lecturerRepository.saveAndFlush(lecturer);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Lecturer changeLecturer(Lecturer lecturer) {
		return lecturerRepository.saveAndFlush(lecturer);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeLecturer(Lecturer lecturer) {
		ArrayList<Course> courses = courseRepository.findAllByLecturers(lecturer);
		courses.forEach(c -> {
			lecturer.removeCourse(c);
			courseRepository.save(c);
			});
		lecturerRepository.delete(lecturer);
	}

}
