package sg.edu.iss.team5.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.team5.services.LecturerService;
import sg.edu.iss.team5.services.StudentService;


@Controller
public class CapsErrorController implements ErrorController {
	
	@Autowired
	private LecturerService lService;
	@Autowired
	private StudentService sService;
	
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 400){
            return "400";
        }else if(statusCode == 403){
            return "403";
        }else if(statusCode == 404){
            return "404";
        }else{
            return "500";
        }
    }
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @GetMapping("/index")
    public void getUsers(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException {
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	char c = username.toCharArray()[0];
    	
    	if (c=='S')
    	{
    		redirectStrategy.sendRedirect(arg0, arg1, "/student/courses/list");
    	}
    	else if (c=='L')
    	{
    		redirectStrategy.sendRedirect(arg0, arg1, "/lecturer/courses/list");
    	}
    	
    	else if (c=='A')
    	{
    		redirectStrategy.sendRedirect(arg0, arg1, "/admin/");
    	}
    	
    	else
    	{
    		redirectStrategy.sendRedirect(arg0, arg1, "/login");
    	}
    	
//        if (arg0.("ADMIN")) {
//            redirectStrategy.sendRedirect(arg0, arg1, "/admin/");
//        }
//        else if (arg0.isUserInRole("LECTURER")) {
//            redirectStrategy.sendRedirect(arg0, arg1, "/lecturer/courses/list");
//        }
//        else if (arg0.isUserInRole("STUDENT")) {
//            redirectStrategy.sendRedirect(arg0, arg1, "/student/courses/list");
//        }
//        else {
//            redirectStrategy.sendRedirect(arg0, arg1, "/login");
//        }
    }
}

