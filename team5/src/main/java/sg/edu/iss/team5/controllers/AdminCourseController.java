package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value="/admin/courses")
//@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminCourseController {

	@Autowired
	private CourseService cService;
	@Autowired
	private EnrolmentService eService;
	@Autowired
	private StudentService sService;
	@Autowired LecturerService lService;
//	
//	@Autowired
//	private UserValidator uValidator;
//
//	@InitBinder("user")
//	private void initUserBinder(WebDataBinder binder) {
//		binder.addValidators(uValidator);
	

	/**
	 * COURSE CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newCoursePage() {
		ModelAndView mav = new ModelAndView("course-new", "course", new Course());
		ArrayList<Course> cList = cService.findAllCourses();
		mav.addObject("clist", cList);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewCourse(@ModelAttribute @Valid Course course, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("course-new");

		ModelAndView mav = new ModelAndView();
		String message = "New course " + course.getCourseID() + " was successfully created.";
		System.out.println(message);
		cService.createCourse(course);
		mav.setViewName("forward:/admin/courses/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listCoursePage() {
		ModelAndView mav = new ModelAndView("course-list");
		ArrayList<Course> cList = cService.findAllCourses();
		
		
		mav.addObject("clist", cList);
		
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editCoursePage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("course-edit");
		Course course = cService.findCourse(id);
		mav.addObject("course", course);
		ArrayList<Course> cList = cService.findAllCourses();
		mav.addObject("clist", cList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute @Valid Course course, BindingResult result,
			@PathVariable String id) throws CourseNotFound {

		if (result.hasErrors())
			return new ModelAndView("course-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/courses/list");
		String message = "Course was successfully updated.";
		System.out.println(message);
		cService.changeCourse(course);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable String id)
			throws CourseNotFound {

		ModelAndView mav = new ModelAndView("forward:/admin/courses/list");
		Course course = cService.findCourse(id);
		cService.removeCourse(course);
		String message = "The course " + course.getCourseID() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}
	
	@RequestMapping(value = "/enroll/list")
	public ModelAndView listEnrolmentPage() {
		ModelAndView mav = new ModelAndView("enroll-list");
		ArrayList<Student_Course> eList = eService.findAllEnrolment();
		mav.addObject("elist", eList);
		return mav;
	}
	
	@RequestMapping(value = "/enroll/create", method = RequestMethod.GET)
	public ModelAndView newCourseEnrollPage() {
		ModelAndView mav = new ModelAndView("enroll-new", "student_course", new Student_Course());
		ArrayList<Student_Course> eList = eService.findAllEnrolment();
		mav.addObject("elist", eList);
		return mav;
	}

	@RequestMapping(value = "/enroll/create", method = RequestMethod.POST)
	public ModelAndView createNewCourseEnroll(@ModelAttribute @Valid Student_Course stu_c, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("enroll-new");

		ModelAndView mav = new ModelAndView();
//		String message = "New enrolment " + stu_c.getSc_ID() + " was successfully created.";
//		System.out.println(message);
//		System.out.println(std_id);
//		System.out.println(course_id);
		eService.createEnrolment(stu_c);
		mav.setViewName("forward:/admin/courses/enroll/list");
		return mav;
	}
	
	@RequestMapping(value = "/enroll/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEnrolmentPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("enroll-edit");
		Student_Course stu_c = eService.findEnrolment(id);
		mav.addObject("stu_c", stu_c);
		ArrayList<Student_Course> eList = eService.findAllEnrolment();
		mav.addObject("elist", eList);
		return mav;
	}

	@RequestMapping(value = "/enroll/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editEnrolment(@ModelAttribute @Valid Student_Course stu_c, BindingResult result,
			@PathVariable String id) throws EnrolmentNotFound {

		if (result.hasErrors())
			return new ModelAndView("enroll-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/courses/enroll/list");
		String message = "Enrolment was successfully updated.";
		System.out.println(message);
		
		eService.changeEnrolment(stu_c);
		return mav;
	}
	
	
	@RequestMapping(value = "/assign/{courseID}", method = RequestMethod.GET)
	public ModelAndView assignCoursePage(@PathVariable("courseID") String courseID,Model model ) {
		ModelAndView mav = new ModelAndView("course-assign");
		Course course = cService.findCourse(courseID);
		ArrayList<Lecturer> lecturer = lService.findAllLecturers();
		
		Lecturer l = new Lecturer();
		model.addAttribute("course", course);
		
		mav.addObject("course", course);
		mav.addObject("l", l);
		mav.addObject("lecturer", lecturer);
		return mav;
	}

	
	@RequestMapping(value = "/assign/{courseID}", method = RequestMethod.POST)
	public ModelAndView assignCourseSubmit(@PathVariable("courseID") String courseID,@ModelAttribute ("selectedLecturer") Lecturer lecturer,BindingResult result) throws CourseNotFound {

		if (result.hasErrors())
			return new ModelAndView("course-list");

		ModelAndView mav = new ModelAndView("forward:/admin/courses/list");
		String message = "Lecturer was successfully assigned to course " + courseID + " .";
		System.out.println(message);
		Course course = cService.findCourse(courseID);
		Lecturer l = lService.findLecturer(lecturer.getLecturerID());
		System.out.println(course.getCourseID());
		System.out.println(lecturer.getLecturerID());	
		cService.assignCourseToLecturer(course.getCourseID(),l.getLecturerID());
		return mav;
	}
	
	@RequestMapping(value = "/assign/remove/{courseID}/{lecturerID}", method = RequestMethod.GET)
	public ModelAndView editAssignPage(@PathVariable("courseID") String courseID,@PathVariable("lecturerID") String lecturerID)throws CourseNotFound {
		ModelAndView mav = new ModelAndView("forward:/admin/courses/list");
		
		Course course = cService.findCourse(courseID);
		Lecturer l = lService.findLecturer(lecturerID);
		
		cService.removeLectureFromCourse(course.getCourseID(),l.getLecturerID());
		String message = "The lecturer " + course.getCourseID() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}

	
	
}
