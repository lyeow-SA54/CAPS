package sg.edu.iss.team5.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team5.helper.role;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.LecturerRepo;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Resource
	private LecturerRepo lecturerRepository;

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
		lecturer.setUser(new User(lecturer.getLecturerID(), role.LECTURER));
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
		lecturerRepository.delete(lecturer);
	}

}
