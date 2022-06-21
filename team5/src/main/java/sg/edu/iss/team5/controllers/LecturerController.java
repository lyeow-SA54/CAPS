package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
//@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class LecturerController {

	@Autowired
	private CourseService cService;
	@Autowired
	private EnrolmentService eService;
	@Autowired
	private StudentService sService;
	@Autowired
	private LecturerService lService;
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

//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView newCoursePage() {
//		ModelAndView mav = new ModelAndView("course-new", "course", new Course());
//		ArrayList<Course> cList = cService.findAllCourses();
//		mav.addObject("clist", cList);
//		return mav;
//	}
//
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public ModelAndView createNewCourse(@ModelAttribute @Valid Course course, BindingResult result) {
//
//		if (result.hasErrors())
//			return new ModelAndView("course-new");
//
//		ModelAndView mav = new ModelAndView();
//		String message = "New course " + course.getCourseID() + " was successfully created.";
//		System.out.println(message);
//		cService.createCourse(course);
//		mav.setViewName("forward:/admin/courses/list");
//		return mav;
//	}

	@RequestMapping(value = "{lcid}/courses/list")
	public ModelAndView listClassPage(@PathVariable String lcid) {
		ModelAndView mav = new ModelAndView("class-list");
		Lecturer lc = lService.findLecturer(lcid);
		ArrayList<Course> cList = cService.findAllLecturerCourses(lc);
		mav.addObject("clist", cList);
		mav.addObject("lecturer", lc);
		return mav;
	}

	@RequestMapping(value = "{lcid}/courses/{cid}/grade/", method = RequestMethod.GET)
	public ModelAndView studentSelectionPage(@PathVariable String lcid, @PathVariable String cid) {
		ModelAndView mav = new ModelAndView("class-student-list");
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(lcid);
		mav.addObject("course", course);
		mav.addObject("lecturer", lecturer);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(course);
		mav.addObject("sclist", scList);
		return mav;
	}
	
	@RequestMapping(value = "{lcid}/courses/{cid}/grade/{sid}", method = RequestMethod.GET)
	public ModelAndView studentGradingPage(@PathVariable String lcid, @PathVariable String cid, @PathVariable String sid) {
		ModelAndView mav = new ModelAndView("class-student-grading");
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(lcid);
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

	@RequestMapping(value = "{lcid}/courses/{cid}/grade/{sid}", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute @Valid Student_Course enrolment, BindingResult result,
			@PathVariable String lcid, @PathVariable String cid, @PathVariable String sid){
		if (result.hasErrors())
			return new ModelAndView("class-student-grading");
		ModelAndView mav = new ModelAndView("class-student-list");
		Course course = cService.findCourse(cid);
		Lecturer lecturer = lService.findLecturer(lcid);
		mav.addObject("course", course);
		mav.addObject("lecturer", lecturer);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(course);
		mav.addObject("sclist", scList);
		String message = "Enrolment was successfully updated.";
		System.out.println(message);
		eService.changeEnrolment(enrolment);
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
}
