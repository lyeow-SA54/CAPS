package sg.edu.iss.team5;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.attoparser.trace.MarkupTraceEvent.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.BeanDefinitionDsl.Role;

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

//		User u1 = new User();
//		u1.setUserId("U00001");
//		u1.setPassword("password");
//		u1.setRole("");
		
		Student s1 = new Student("S00001", "Mary", "mary@u.nus.edu");
		s1.setStartDate(LocalDate.parse("2022-05-25"));
		s1.setEventType(status.APPROVED);
		s1.setGpa(8.0);
//		s1.setUser("U00001",role.STUDENT);
//		s1.setUser(studentuser.role.STUDENT);
		
		Student s2 = new Student("S00002", "John", "john@u.nus.edu");
		s2.setStartDate(LocalDate.parse("2022-01-24"));
		s2.setEventType(status.APPROVED);
		s2.setGpa(12.0);
		//s2.setUser(User("S00001",role.STUDENT));

		
		Student s3 = new Student("S00003", "Lily", "lily@u.nus.edu");
		s3.setStartDate(LocalDate.parse("2022-03-19"));
		s3.setEventType(status.INPROGRESS);
		s3.setGpa(14.0);
		//s3.setUser(User("S00001",role.STUDENT));

		
		Student s4 = new Student("S00004", "Peter", "peter@u.nus.edu");
		s4.setStartDate(LocalDate.parse("2022-02-19"));
		s4.setEventType(status.UPDATED);
		s4.setGpa(7.0);
		//s4.setUser(User("S00001",role.STUDENT));
		
		
		Student s5 = new Student("S00005", "Bobby", "bobby@u.nus.edu");
		s5.setStartDate(LocalDate.parse("2022-09-17"));
		s5.setEventType(status.INPROGRESS);
		s5.setGpa(8.0);
		//s5.setUser(User("S00001",role.STUDENT));
		
		Student s6 = new Student("S00006", "James", "james@u.nus.edu");
		s6.setStartDate(LocalDate.parse("2022-08-05"));
		s6.setEventType(status.UPDATED);
		s6.setGpa(32.0);
		//s1.setUser(User("S00001",role.STUDENT));;
		
		Student s7 = new Student("S00007", "Robert", "robert@u.nus.edu");
		s7.setStartDate(LocalDate.parse("2022-07-24"));
		s7.setEventType(status.INPROGRESS);
		s7.setGpa(19.0);
		//s2.setUser(User("S00001",role.STUDENT));
		
		Student s8 = new Student("S00008", "Ella", "ella@u.nus.edu");
		s8.setStartDate(LocalDate.parse("2022-08-09"));
		s8.setEventType(status.INPROGRESS);
		s8.setGpa(19.0);
		//s3.setUser(User("S00001",role.STUDENT));
		
		Student s9 = new Student("S00009", "Nova", "nova@u.nus.edu");
		s9.setStartDate(LocalDate.parse("2022-05-16"));
		s9.setEventType(status.APPROVED);
		s9.setGpa(35.0);
		//s4.setUser(User("S00001",role.STUDENT));
		
		
		Student s10 = new Student("S00010", "Elizabeth", "elizabeth@u.nus.edu");
		s10.setStartDate(LocalDate.parse("2022-04-03"));
		s10.setEventType(status.WITHDRAWN);
		s10.setGpa(8.0);
		//s5.setUser(User("S00001",role.STUDENT));
		
		
		Course c1 = new Course();
		c1.setCourseID("SA4102");
		c1.setCode("SA4102");
		c1.setName("Enterprise Solutions Design and Development");
		c1.setCourseDays(80);
		c1.setStartDate(LocalDate.parse("2022-05-06"));
		c1.setDescription("Attain knowledge on basic programming and objected oriented programming with C#");
		c1.setRoom("Beacon");
		c1.setCredits(8.0);
		c1.setLessonDay("Mon to Fri");
		c1.setFee(2500.00);
		c1.setClassPax(45);
		c1.setMaxCap(60);
		cr.save(c1);
		
		Course c2 = new Course();
		c2.setCourseID("SA4101");
		c2.setCode("SA4101");
		c2.setName("Software Analysis and Design");
		c2.setCourseDays(80);
		c2.setStartDate(LocalDate.parse("2022-08-09"));
		c2.setDescription("Ability to conduct user requirement through user experience design and agile practices; Analyse and design software solutions to solve business problems");
		c2.setRoom("Beacon");
		c2.setCredits(6.0);
		c2.setLessonDay("Mon to Fri");
		c2.setFee(3250.00);
		c2.setClassPax(60);
		c2.setMaxCap(100);
		cr.save(c2);
		
		Course c3 = new Course();
		c3.setCourseID("SA4110");
		c3.setCode("SA4110");
		c3.setName("Machine Learning Application Development");
		c3.setCourseDays(80);
		c3.setStartDate(LocalDate.parse("2022-12-14"));
		c3.setDescription("Understands popular machine learning models such as k-nearest neighbours, random forest, logistic regression, k-means, naïve Bayes and artificial neural network to build and evaluate performance of machine learning models using Python");
		c3.setRoom("Beacon");
		c3.setCredits(6.0);
		c3.setLessonDay("Mon to Fri");
		c3.setFee(2800.00);
		c3.setClassPax(30);
		c3.setMaxCap(70);
		cr.save(c3);
		
		Course c4 = new Course();
		c4.setCourseID("SA4105");
		c4.setCode("SA4105");
		c4.setName("Web Application Development");
		c4.setCourseDays(80);
		c4.setStartDate(LocalDate.parse("2022-01-14"));
		c4.setDescription("Learns the techniques and engineering skills needed for the end-to-end design, architecture, implementation, persistence and testing an enterprise web application");
		c4.setRoom("Beacon");
		c4.setCredits(6.0);
		c4.setLessonDay("Mon to Fri");
		c4.setFee(2580.00);
		c4.setClassPax(35);
		c4.setMaxCap(50);
		cr.save(c4);
		
		Course c5 = new Course();
		c5.setCourseID("SA4106");
		c5.setCode("SA4106");
		c5.setName("Mobile Application Development");
		c5.setCourseDays(80);
		c5.setStartDate(LocalDate.parse("2023-04-08"));
		c5.setDescription("Understanding Android programming framework, developing and packaging Android application using Android Studio");
		c5.setRoom("Beacon");
		c5.setCredits(6.0);
		c5.setLessonDay("Mon to Fri");
		c5.setFee(3520.00);
		c5.setClassPax(55);
		c5.setMaxCap(75);
		cr.save(c5);
		

		
		
//		Lecturer l1 = new Lecturer();
//		l1.setLecturerID("L00001");
//		l1.setName("Esther");
//		l1.setEmail("esther@u.nus.edu");
//		lcr.save(l1);
		
		Lecturer l1 = new Lecturer("L00001");
		l1.setName("Esther");
		l1.setEmail("esther@u.nus.edu");
		Set<Course> courselist1 = new HashSet<Course>();
		courselist1.add(c1);
		courselist1.add(c2);
		l1.setTeachings(courselist1);
		lcr.save(l1);
		
		Lecturer l2 = new Lecturer("L00002");
		l2.setName("Suria");
		l2.setEmail("suria@u.nus.edu");
		Set<Course> courselist2 = new HashSet<Course>();
		courselist2.add(c2);
		courselist2.add(c3);
		l2.setTeachings(courselist2);
		lcr.save(l2);
		
		Lecturer l3 = new Lecturer("L00003");
		l3.setName("Cherwah");
		l3.setEmail("cherwah@u.nus.edu");
		Set<Course> courselist3 = new HashSet<Course>();
		courselist3.add(c3);
		courselist3.add(c5);
		l3.setTeachings(courselist3);
		lcr.save(l3);
		
		Lecturer l4 = new Lecturer("L00004");
		l4.setName("Yuenkwan");
		l4.setEmail("yuenkwan@u.nus.edu");
		Set<Course> courselist4 = new HashSet<Course>();
		courselist4.add(c1);
		courselist4.add(c2);
		courselist4.add(c5);
		l4.setTeachings(courselist4);
		lcr.save(l4);
		
		Lecturer l5 = new Lecturer("L00005");
		l5.setName("Tin");
		l5.setEmail("Tin@u.nus.edu");
		Set<Course> courselist5 = new HashSet<Course>();
		courselist5.add(c1);
		courselist5.add(c2);
		courselist5.add(c4);
		courselist5.add(c5);
		l5.setTeachings(courselist5);
		lcr.save(l5);
		
		
//		Admin a1 = new Admin();
//		a1.setUserId(00001);
//		a1.setName("Jane");
//		ar.save(a1);
//		
//		Admin a2 = new Admin();
//		a2.setUserId(00002);
//		a2.setName("Susan");
//		ar.save(a2);
//		
//		Admin a3 = new Admin();
//		a3.setUserId(00003);
//		a3.setName("Jenny");
//		ar.save(a3);
//		
//		Admin a4 = new Admin();
//		a4.setUserId(00004);
//		a4.setName("Bob");
//		ar.save(a4);
//		
//		Admin a5 = new Admin();
//		a5.setUserId(00005);
//		a5.setName("Brian");
//		ar.save(a5);
		
		
		
		Student_Course sc1 = new Student_Course(s1, c1, 0, status.INPROGRESS);
		sc1.setSc_ID("SC00001");
		Set<Student_Course> sclist = Set.of(sc1);
		s1.setStudyList(sclist);
		c1.setClassList(sclist);
		sc1.setScore(80);

			
		Student_Course sc2 = new Student_Course(s1, c3, 0, status.APPROVED);
		sc2.setSc_ID("SC00002");
		Set<Student_Course> sclist2 = Set.of(sc2);
		s1.setStudyList(sclist2);
		c3.setClassList(sclist2);
		sc2.setScore(0);

		
		Student_Course sc3 = new Student_Course(s1, c5, 0, status.SUBMITTED);
		sc3.setSc_ID("SC00003");
		Set<Student_Course> sclist3 = Set.of(sc3);
		s1.setStudyList(sclist3);
		c5.setClassList(sclist3);
		sc3.setScore(0);
		sr.save(s1);
		
		Student_Course sc4 = new Student_Course(s2, c2, 0, status.INPROGRESS);
		sc4.setSc_ID("SC00004");
		Set<Student_Course> sclist4 = Set.of(sc4);
		s2.setStudyList(sclist4);
		c2.setClassList(sclist4);
		sc4.setScore(25);

		
		Student_Course sc5 = new Student_Course(s2, c4, 0, status.UPDATED);
		sc5.setSc_ID("SC00005");
		Set<Student_Course> sclist5 = Set.of(sc5);
		s2.setStudyList(sclist5);
		c4.setClassList(sclist5);
		sc5.setScore(45);
		sr.save(s2);
		
		Student_Course sc6 = new Student_Course(s3, c3, 0, status.INPROGRESS);
		sc6.setSc_ID("SC00006");
		Set<Student_Course> sclist6 = Set.of(sc6);
		s3.setStudyList(sclist6);
		c3.setClassList(sclist6);
		sc6.setScore(40);
		
		Student_Course sc7 = new Student_Course(s3, c5, 0, status.APPROVED);
		sc7.setSc_ID("SC00007");
		Set<Student_Course> sclist7 = Set.of(sc7);
		s2.setStudyList(sclist7);
		c5.setClassList(sclist7);
		sc7.setScore(0);
		sr.save(s3);
		
		Student_Course sc8 = new Student_Course(s4, c1, 0, status.INPROGRESS);
		sc8.setSc_ID("SC00008");
		Set<Student_Course> sclist8 = Set.of(sc8);
		s4.setStudyList(sclist8);
		c1.setClassList(sclist8);
		sc8.setScore(45);
		
		Student_Course sc9 = new Student_Course(s4, c4, 0, status.INPROGRESS);
		sc9.setSc_ID("SC00009");
		Set<Student_Course> sclist9 = Set.of(sc9);
		s4.setStudyList(sclist9);
		c4.setClassList(sclist9);
		sc9.setScore(35);
		
		Student_Course sc10 = new Student_Course(s4, c5, 0, status.REJECTED);
		sc10.setSc_ID("SC00010");
		Set<Student_Course> sclist10 = Set.of(sc10);
		s4.setStudyList(sclist10);
		c5.setClassList(sclist10);
		sc10.setScore(0);
		sr.save(s4);

		Student_Course sc11 = new Student_Course(s5, c2, 0, status.INPROGRESS);
		sc11.setSc_ID("SC00011");
		Set<Student_Course> sclist11 = Set.of(sc11);
		s5.setStudyList(sclist11);
		c2.setClassList(sclist11);
		sc11.setScore(35);
		
		Student_Course sc12 = new Student_Course(s5, c3, 0, status.APPROVED);
		sc12.setSc_ID("SC00012");
		Set<Student_Course> sclist12 = Set.of(sc12);
		s5.setStudyList(sclist12);
		c3.setClassList(sclist12);
		sc12.setScore(0);
		
		Student_Course sc13 = new Student_Course(s5, c5, 0, status.SUBMITTED);
		sc13.setSc_ID("SC00013");
		Set<Student_Course> sclist13 = Set.of(sc13);
		s5.setStudyList(sclist13);
		c5.setClassList(sclist13);
		sc13.setScore(35);
		sr.save(s5);
		
		Student_Course sc14 = new Student_Course(s6, c1, 0, status.INPROGRESS);
		sc14.setSc_ID("SC00014");
		Set<Student_Course> sclist14 = Set.of(sc14);
		s6.setStudyList(sclist14);
		c1.setClassList(sclist14);
		sc14.setScore(50);
		
		Student_Course sc15 = new Student_Course(s6, c4, 0, status.UPDATED);
		sc15.setSc_ID("SC00015");
		Set<Student_Course> sclist15 = Set.of(sc15);
		s6.setStudyList(sclist15);
		c4.setClassList(sclist15);
		sc15.setScore(55);
		
		Student_Course sc16 = new Student_Course(s6, c5, 0, status.APPROVED);
		sc16.setSc_ID("SC00016");
		Set<Student_Course> sclist16 = Set.of(sc16);
		s6.setStudyList(sclist16);
		c5.setClassList(sclist16);
		sc16.setScore(50);
		sr.save(s6);
		
		Student_Course sc17 = new Student_Course(s7, c2, 0, status.INPROGRESS);
		sc17.setSc_ID("SC00017");
		Set<Student_Course> sclist17 = Set.of(sc17);
		s7.setStudyList(sclist17);
		c2.setClassList(sclist17);
		sc17.setScore(40);
		
		Student_Course sc18 = new Student_Course(s7, c3, 0, status.GRADUATED);
		sc18.setSc_ID("SC00018");
		Set<Student_Course> sclist18 = Set.of(sc18);
		s7.setStudyList(sclist18);
		c3.setClassList(sclist18);
		sc18.setScore(90);
		
		Student_Course sc19 = new Student_Course(s7, c5, 0, status.UPDATED);
		sc19.setSc_ID("SC00019");
		Set<Student_Course> sclist19 = Set.of(sc19);
		s7.setStudyList(sclist19);
		c5.setClassList(sclist19);
		sc19.setScore(55);
		sr.save(s7);
		
		Student_Course sc20 = new Student_Course(s8, c2, 0, status.INPROGRESS);
		sc20.setSc_ID("SC00020");
		Set<Student_Course> sclist20 = Set.of(sc20);
		s8.setStudyList(sclist20);
		c2.setClassList(sclist20);
		sc20.setScore(55);
		
		Student_Course sc21 = new Student_Course(s8, c3, 0, status.APPROVED);
		sc21.setSc_ID("SC00021");
		Set<Student_Course> sclist21 = Set.of(sc21);
		s8.setStudyList(sclist21);
		c3.setClassList(sclist21);
		sc21.setScore(0);
		
		Student_Course sc22 = new Student_Course(s8, c4, 0, status.GRADUATED);
		sc22.setSc_ID("SC00022");
		Set<Student_Course> sclist22 = Set.of(sc22);
		s8.setStudyList(sclist22);
		c4.setClassList(sclist22);
		sc22.setScore(85);
		sr.save(s8);
		
		Student_Course sc23 = new Student_Course(s9, c1, 0, status.INPROGRESS);
		sc23.setSc_ID("SC00023");
		Set<Student_Course> sclist23 = Set.of(sc23);
		s9.setStudyList(sclist23);
		c1.setClassList(sclist23);
		sc23.setScore(32);
		
		Student_Course sc24 = new Student_Course(s9, c3, 0, status.SUBMITTED);
		sc24.setSc_ID("SC00024");
		Set<Student_Course> sclist24 = Set.of(sc24);
		s9.setStudyList(sclist24);
		c3.setClassList(sclist24);
		sc24.setScore(0);
		
		Student_Course sc25 = new Student_Course(s9, c4, 0, status.APPROVED);
		sc25.setSc_ID("SC00025");
		Set<Student_Course> sclist25 = Set.of(sc25);
		s9.setStudyList(sclist25);
		c4.setClassList(sclist25);
		sc25.setScore(0);
		sr.save(s9);
		
		Student_Course sc26 = new Student_Course(s10, c2, 0, status.GRADUATED);
		sc26.setSc_ID("SC00026");
		Set<Student_Course> sclist26 = Set.of(sc26);
		s10.setStudyList(sclist26);
		c2.setClassList(sclist26);
		sc26.setScore(60);
		
		Student_Course sc27 = new Student_Course(s10, c4, 0, status.REJECTED);
		sc27.setSc_ID("SC00027");
		Set<Student_Course> sclist27 = Set.of(sc27);
		s10.setStudyList(sclist27);
		c4.setClassList(sclist27);
		sc27.setScore(60);
		
		Student_Course sc28 = new Student_Course(s10, c5, 0, status.REJECTED);
		sc28.setSc_ID("SC00028");
		Set<Student_Course> sclist28 = Set.of(sc28);
		s10.setStudyList(sclist28);
		c5.setClassList(sclist28);
		sc28.setScore(0);
		sr.save(s10);

    }

}
