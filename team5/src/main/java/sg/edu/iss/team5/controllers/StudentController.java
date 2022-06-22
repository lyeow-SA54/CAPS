package sg.edu.iss.team5.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.services.CourseService;
import sg.edu.iss.team5.services.EnrolmentService;
import sg.edu.iss.team5.services.StudentService;

@Controller
@RequestMapping(value="/student")
public class StudentController {
	
	@Autowired
	private CourseService cService;
	@Autowired
	private StudentService sService;
	@Autowired
	private EnrolmentService eService;
	
	//find courses enrolled
	@RequestMapping(value="/courses/list")
	public ModelAndView showEnrolledPage() {
		ModelAndView mav = new ModelAndView("student-enroll-list");
		//create enrolled-list
		//create new html to show only enrolled classes & remove add course enrollment function on html
		//need to add if completed/inprogress/new
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Student s = sService.findStudent(username);
		ArrayList<Student_Course>eList = eService.findAllEnrolmentByStudent(s);
		mav.addObject("elist", eList);
		return mav;		
	}
	
	//view grade and gpa
	@RequestMapping(value="/courses/view/{scid}",method = RequestMethod.GET)
	public ModelAndView viewGradePage(@PathVariable String scid) {
		ModelAndView mav = new ModelAndView("student-enroll-view");//create grade-list
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		Student student = sService.findStudent(username);
//		Course course = cService.findCourse(cid);
		Student_Course enrolment = eService.findEnrolment(scid);
		mav.addObject("enrolment", enrolment);
		return mav;	
	}
	
//	//enroll for a course
//	@RequestMapping(value="/courses/enroll")
//	public ModelAndView listEnrolmentPage() {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		ModelAndView mav = new ModelAndView("enroll-list");
//		ArrayList<Student_Course>eList = eService.findAllEnrolment();
//		mav.addObject("elist", eList);
//		return mav;		
//	}
	
	@RequestMapping(value = "/courses/enroll", method = RequestMethod.GET)
	public ModelAndView newCourseEnrollPage() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView mav = new ModelAndView("student-enroll-new");
		Student student = sService.findStudent(username);
		ArrayList<Course>cList = eService.findAvailableEnrolmentByStudent(student);
		mav.addObject("clist", cList);
		return mav;
	}
	
	@RequestMapping(value = "/courses/enroll/{cid}", method = RequestMethod.GET)
    public ModelAndView createNewCourseEnroll(@PathVariable String cid) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("student-enroll-new");
        Student student = sService.findStudent(username);
        Course course = cService.findCourse(cid);
        eService.createEnrolment(new Student_Course(student, course, status.SUBMITTED));
        ArrayList<Course>cList = eService.findAvailableEnrolmentByStudent(student);
        mav.addObject("clist", cList);
        return mav;     
    }
}
	

	
