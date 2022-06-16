package sg.edu.iss.team5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.BeanDefinitionDsl.Role;

import sg.edu.iss.team5.helper.role;
import sg.edu.iss.team5.helper.status;
import sg.edu.iss.team5.model.Admin;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.model.User;
import sg.edu.iss.team5.repositories.AdminRepo;
import sg.edu.iss.team5.repositories.StudentRepo;
import sg.edu.iss.team5.repositories.UserRepo;



@SpringBootApplication
//public class Team5Application{
//	public static void main(String[] args) {
//		SpringApplication.run(Team5Application.class, args);
//	}
	
	public class Team5Application
	implements CommandLineRunner {
	@Autowired AdminRepo ar;
	@Autowired UserRepo ur;
	@Autowired StudentRepo sr;
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
		Admin a1 = new Admin("LEON");
		ar.save(a1);
//		User s1a = new User("S123456", role.valueOf("STUDENT"));
//		ur.save(s1a);
		Student s1 = new Student("S123456", "Student1", "abc@email.com");
		sr.save(s1);
    }

}
