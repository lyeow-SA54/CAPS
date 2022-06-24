package sg.edu.iss.team5.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class ErrorRedirectController implements ErrorController {
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
        if (c == 'S') {
            redirectStrategy.sendRedirect(arg0, arg1, "/student/courses/list");
        }
        else if (c == 'L') {
            redirectStrategy.sendRedirect(arg0, arg1, "/lecturer/courses/list");
        }
        else if (c == 'L') {
            redirectStrategy.sendRedirect(arg0, arg1, "/admin");
        }
        else {
            redirectStrategy.sendRedirect(arg0, arg1, "/login");
        }
    }
}

