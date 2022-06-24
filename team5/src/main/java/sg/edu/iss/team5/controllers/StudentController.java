package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class StudentController<T> {
	
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
		Double avgScore = eList.stream()
				.mapToDouble(x->x.getScore())
				.average().getAsDouble();
		if (avgScore >= 93 && avgScore <= 100) {
			s.setGpa(5.0);
		} else if (avgScore >= 90 && avgScore <= 92) {
			s.setGpa(4.7);
		} else if (avgScore >= 87 && avgScore <= 89) {
			s.setGpa(4.3);
		} else if (avgScore >= 83 && avgScore <= 86) {
			s.setGpa(4.0);
		} else if (avgScore >= 80 && avgScore <= 82) {
			s.setGpa(3.7);
		} else if (avgScore >= 77 && avgScore <= 79) {
			s.setGpa(3.3);
		} else if (avgScore >= 73 && avgScore <= 76) {
			s.setGpa(3.0);
		} else if (avgScore >= 70 && avgScore <= 72) {
			s.setGpa(2.7);
		} else if (avgScore >= 67 && avgScore <= 69) {
			s.setGpa(2.3);
		} else if (avgScore >= 65 && avgScore <= 66) {
			s.setGpa(2.0);
		} else {
			s.setGpa(0.0);
		}
		mav.addObject("student", s);
		return mav;		
	}
	
	//view grade and gpa
	@RequestMapping(value="/courses/view/{cid}",method = RequestMethod.GET)
	public ModelAndView viewGradePage(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("student-enrolledcourse-view");//create grade-list
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		Student student = sService.findStudent(username);
//		Course course = cService.findCourse(cid);
		Course course = cService.findCourse(cid);
//		Student student = sService.findStudent(scid);
		mav.addObject("course", course);
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
		ArrayList<Course> List = cService.findAllCourses();
		for (Course c : List) {
			ArrayList<Student_Course> classList = eService.findAllEnrolmentByCourse(c);
			c.setClassPax(classList.size());
		}

		List<Course> courselist = cList.stream()
					.filter(x -> x.getClassPax()<x.getMaxCap())
					.collect(Collectors.toList());
		
		mav.addObject("courselist", courselist);
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
        mav.addObject("courselist", cList);
        return mav;
    }
}
	

	
