package sg.edu.iss.team5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Admin;
import sg.edu.iss.team5.model.Course;
import sg.edu.iss.team5.model.Lecturer;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.Student_Course;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.AdminRepo;
import sg.edu.iss.team5.repositories.CourseRepo;
import sg.edu.iss.team5.repositories.EnrolmentRepo;
import sg.edu.iss.team5.repositories.LecturerRepo;
import sg.edu.iss.team5.repositories.StudentRepo;
import sg.edu.iss.team5.repositories.UserRepo;



@SpringBootApplication
@EnableWebSecurity
//public class Team5Application{
//	public static void main(String[] args) {
//		SpringApplication.run(Team5Application.class, args);
//	}
	
	public class Team5Application
	implements CommandLineRunner {
	@Autowired AdminRepo ar;
	@Autowired UserRepo ur;
	@Autowired StudentRepo sr;
	@Autowired CourseRepo cr;
	@Autowired LecturerRepo lcr;
	@Autowired EnrolmentRepo er;
	public static void main(String[] args) {
		SpringApplication.run(Team5Application.class, args);

	}
	
//	public void run(ApplicationContext ctx) {
//	return args -> {
//		Admin a1 = new Admin("LEON");
//		ar.save(a1);
//		User s1 = new User("A12345", "Student");
//		ur.save(s1);
//	};
//	}
	
	@Override
    public void run(String... args) throws Exception
    {
		Student s1 = new Student("S12345", "Student1", "abc@email.com");
		Course c1 = new Course();
		c1.setCourseID("C12345");
		cr.save(c1);
		Course c2 = new Course();
		c2.setCourseID("C22345");
		cr.save(c2);
		Student_Course sc1 = new Student_Course(s1, c1, 0, status.GRADUATED);
		sc1.setSc_ID("SC12345");
		Set<Student_Course> sclist = Set.of(sc1);
		s1.setStudyList(sclist);
		c1.setClassList(sclist);
		sr.save(s1);
		Lecturer l1 = new Lecturer("L12345");
		Set<Course> courselist = new HashSet<Course>();
		courselist.add(c1);
		courselist.add(c2);
		l1.setTeachings(courselist);
		lcr.save(l1);
		
		
		User a = new User();
		a.setUsername("a1");
		a.setPassword("123");
		
		
    }

}
