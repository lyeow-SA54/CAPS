package sg.edu.iss.team5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import sg.edu.iss.team5.services.LecturerService;
import sg.edu.iss.team5.services.StudentService;

public class Greeting {

	  private String message;

	  public Greeting() {
	  }

	  public Greeting(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return this.message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	  @Override
	  public String toString() {
	    return "Greeting{" +
	        "message='" + message + '\'' +
	        '}';
	  }
	}

	
//	@Autowired
//	private LecturerService lService;
//	@Autowired
//	private StudentService sService;
//
//  @Override
//  public String toString() {
//	  
//	String username = SecurityContextHolder.getContext().getAuthentication().getName();
//	char c = username.toCharArray()[0];
//	String name = "";
//	if (c=='L')
//	{
//		name = lService.findLecturer(username).getName();
//	}
//	else if (c=='L')
//	{
//		name = sService.findStudent(username).getName();
//	}
//    return "Welcome, " + name + "!";
	  
//  }
//}
