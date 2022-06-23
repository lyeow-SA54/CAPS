package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team5.exception.CourseNotFound;
import sg.edu.iss.team5.exception.EnrolmentNotFound;
import sg.edu.iss.team5.helper.Request;
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
@RequestMapping(value = "/lecturer")
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
		for (Course c : cList) {
			ArrayList<Student_Course> classList = eService.findAllEnrolmentByCourse(c);
			c.setClassPax(classList.size());
		}
		mav.addObject("clist", cList);
		mav.addObject("lecturer", lc);

		return mav;
	}

	@GetMapping("courses/{cid}")
	public ModelAndView classList(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("class-student-list");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Lecturer lc = lService.findLecturer(username);
		Course c = cService.findCourse(cid);
		ArrayList<Student_Course> scList = eService.findAllEnrolmentByCourse(c);

		mav.addObject("lecturer", lc);
		mav.addObject("sclist", scList);
		mav.addObject("course", c);
		mav.addObject("stuCourse", new Student_Course());

		return mav;
	}

	@RequestMapping(value = "/courses/{cid}/{sid}", method = RequestMethod.POST)
	public ModelAndView studentGradingPage(@PathVariable("cid") String cid, @PathVariable("sid") String sid,
			@ModelAttribute Student_Course enrolment) {
		System.out.println(enrolment.getScore());
		ModelAndView mav = new ModelAndView("class-student-list");

		System.out.println(1);
		Course course = cService.findCourse(cid);
		Student s = sService.findStudent(sid);

		Student_Course sc = eService.findEnrolmentByCourseAndStudent(course, s);
		sc.setScore(enrolment.getScore());

		String message = "Student " + s.getStudentID() + " grade has been updated.";
		System.out.println(message);

		eService.changeEnrolment(sc);

		System.out.println(5);
		if (sc.getScore() >= 93 && sc.getScore() <= 100) {
			s.setGpa(5.0);
		} else if (sc.getScore() >= 90 && sc.getScore() <= 92) {
			s.setGpa(4.7);
		} else if (sc.getScore() >= 87 && sc.getScore() <= 89) {
			s.setGpa(4.3);
		} else if (sc.getScore() >= 83 && sc.getScore() <= 86) {
			s.setGpa(4.0);
		} else if (sc.getScore() >= 80 && sc.getScore() <= 82) {
			s.setGpa(3.7);
		} else if (sc.getScore() >= 77 && sc.getScore() <= 79) {
			s.setGpa(3.3);
		} else if (sc.getScore() >= 73 && sc.getScore() <= 76) {
			s.setGpa(3.0);
		} else if (sc.getScore() >= 70 && sc.getScore() <= 72) {
			s.setGpa(2.7);
		} else if (sc.getScore() >= 67 && sc.getScore() <= 69) {
			s.setGpa(2.3);
		} else if (sc.getScore() >= 65 && sc.getScore() <= 66) {
			s.setGpa(2.0);
		} else {
			s.setGpa(0.0);
		}
		System.out.println(6);
		sService.changeStudent(s);

		System.out.println(7);
		mav = new ModelAndView("redirect:/lecturer/courses/" + cid);

		return mav;
	}

//	

	@RequestMapping(value = "/courses/{cid}/editstatus/{sid}", method = RequestMethod.GET)
	public ModelAndView sendRequest(@PathVariable ("cid")String cid, @PathVariable ("sid")String sid) {
		
		ModelAndView mav = new ModelAndView("class-student-editstatus");
		
		Course course = cService.findCourse(cid);
		Student student = sService.findStudent(sid);
		mav.addObject("request", new Request());
		mav.addObject("student", student);
		mav.addObject("course",course);
		return mav;
		}
		
	@RequestMapping(value = "/courses/{cid}/editstatus/{sid}", method = RequestMethod.POST)
	public ModelAndView sendPendingRequest(@ModelAttribute("request") Request request,@PathVariable String cid, @PathVariable String sid) {
		
		
		Course course = cService.findCourse(cid);
		Student student = sService.findStudent(sid);
		Student_Course sc = eService.findEnrolmentByCourseAndStudent(course, student);
		ModelAndView mav = new ModelAndView("class-student-editstatus");

		if (request.getDecision().trim().equalsIgnoreCase(status.APPROVED.toString())) {
			student.setEventType(status.APPROVED);
			sService.changeStudent(student);
		} else {
			student.setEventType(status.REJECTED);
			sService.changeStudent(student);
		}
		// ce.setEventBy(usession.getEmployee().getEmployeeId());
		student.setComment(request.getComment());

		eService.changeEnrolment(sc);

		mav = new ModelAndView("redirect:/lecturer/courses/" + cid);
		String message = "Request for student " + student.getStudentID() +" been sent to admin for update.";
		System.out.println(message);
		return mav;
	}

}
