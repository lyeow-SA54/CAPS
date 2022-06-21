package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.services.CourseService;
import sg.edu.iss.team5.services.EnrolmentService;
import sg.edu.iss.team5.services.LecturerService;
import sg.edu.iss.team5.services.StudentService;

@Controller
@RequestMapping(value="/lecturer")
public class LecturerController {

	@Autowired
	private CourseService cService;
	@Autowired
	private EnrolmentService eService;
	@Autowired
	private StudentService sService;
	@Autowired LecturerService lService;

	@RequestMapping(value = "/viewCourse/{lecturerID}")
	public ModelAndView viewCoursePage(@PathVariable("lecturerID")String lecturerID) {
		ModelAndView mav = new ModelAndView("lecturer-course");
		
		Lecturer lecturer = lService.findLecturer(lecturerID);
		Set<Course> teachList =  lecturer.getTeachings();
		
		mav.addObject("teachList", teachList);
		return mav;
	}
}
