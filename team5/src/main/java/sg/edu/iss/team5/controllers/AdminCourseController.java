package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.services.CourseService;


@Controller
@RequestMapping(value="/admin/courses")
//@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminCourseController {

	@Autowired
	private CourseService cService;
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
}
