package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team5.exception.CourseNotFound;
import sg.edu.iss.team5.exception.EnrolmentNotFound;
import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;
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
	@Autowired
	private LecturerService lService;

	/**
	 * COURSE CRUD OPERATIONS
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/courses/list")
	public ModelAndView listClassPage() {
		ModelAndView mav = new ModelAndView("class-list");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Lecturer lc = lService.findLecturer(username);
		ArrayList<Course> cList = cService.findAllLecturerCourses(lc);
		mav.addObject("clist", cList);
		mav.addObject("lecturer", lc);
		return mav;
	}

	@RequestMapping(value = "/courses/{cid}/grade/", method = RequestMethod.GET)
	public ModelAndView studentSelectionPage(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("class-student-list");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(username);
		mav.addObject("course", course);
		mav.addObject("lecturer", lecturer);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(course);
		mav.addObject("sclist", scList);
		return mav;
	}
	
	@RequestMapping(value = "/courses/{cid}/grade/{sid}", method = RequestMethod.GET)
	public ModelAndView studentGradingPage(@PathVariable String cid, @PathVariable String sid) {
		ModelAndView mav = new ModelAndView("class-student-grading");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(username);
		Student student = sService.findStudent(sid);
		mav.addObject("course", course);
		mav.addObject("lecturer", lecturer);
		mav.addObject("student", student);
		Student_Course sc = eService.findEnrolmentByCourseAndStudent(course, student);
		mav.addObject("enrolment", sc);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(course);
		mav.addObject("sclist", scList);
		return mav;
	}

	@RequestMapping(value = "/courses/{cid}/grade/{sid}", method = RequestMethod.POST)
	public ModelAndView studentGradingPage(@ModelAttribute @Valid Student_Course enrolment, BindingResult result, @PathVariable String cid, @PathVariable String sid){
		if (result.hasErrors())
			return new ModelAndView("class-student-grading");
		ModelAndView mav = new ModelAndView("class-student-list");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(username);
		mav.addObject("course", course);
		mav.addObject("lecturer", lecturer);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(course);
		mav.addObject("sclist", scList);
		String message = "Enrolment was successfully updated.";
		System.out.println(message);
		eService.changeEnrolment(enrolment);
		return mav;
	}

}
