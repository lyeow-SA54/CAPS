package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Lecturer;

public interface LecturerService {

	ArrayList<Lecturer> findAllLecturers();
	
	Lecturer findLecturer(String id);

	Lecturer createLecturer(Lecturer lecturer);

	Lecturer changeLecturer(Lecturer lecturer);

	void removeLecturer(Lecturer lecturer);	

}
