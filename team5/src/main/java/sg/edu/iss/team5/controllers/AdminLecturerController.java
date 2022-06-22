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

import sg.edu.iss.team5.exception.LecturerNotFound;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.services.LecturerService;


@Controller
@RequestMapping(value="/admin/lecturers")
//@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminLecturerController {

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newLecturerPage() {
		ModelAndView mav = new ModelAndView("lecturer-new", "lecturer", new Lecturer());
		ArrayList<Lecturer> lcList = lService.findAllLecturers();
		mav.addObject("lclist", lcList);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewLecturer(@ModelAttribute @Valid Lecturer lecturer, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("lecturer-new");

		ModelAndView mav = new ModelAndView();
		String message = "New lecturer " + lecturer.getLecturerID() + " was successfully created.";
		System.out.println(message);
		lService.createLecturer(lecturer);
		mav.setViewName("forward:/admin/lecturers/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listLecturerPage() {
		ModelAndView mav = new ModelAndView("lecturer-list");
		ArrayList<Lecturer> lcList = lService.findAllLecturers();
		mav.addObject("lclist", lcList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editLecturerPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("lecturer-edit");
		Lecturer lecturer = lService.findLecturer(id);
		mav.addObject("lecturer", lecturer);
		ArrayList<Lecturer> lcList = lService.findAllLecturers();
		mav.addObject("lclist", lcList);
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute @Valid Lecturer lecturer, BindingResult result) throws LecturerNotFound {

		if (result.hasErrors())
			return new ModelAndView("lecturer-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/lecturers/list");
		String message = "Lecturer was successfully updated.";
		System.out.println(message);
		lService.changeLecturer(lecturer);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteLecturer(@PathVariable String id)
			throws LecturerNotFound {

		ModelAndView mav = new ModelAndView("forward:/admin/lecturers/list");
		Lecturer lecturer = lService.findLecturer(id);
		lService.removeLecturer(lecturer);
		String message = "The lecturer " + lecturer.getLecturerID() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}
}

