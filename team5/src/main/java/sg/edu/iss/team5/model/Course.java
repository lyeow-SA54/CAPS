package sg.edu.iss.team5.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@NoArgsConstructor
public class Course {

	@Id
	private String courseID;
	private String code;
	private String name;
	private int courseDays;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	private String description;
	private String room;
	private double credits;
	private String lessonDay;
	private double fee;
	private int classPax;
	private int maxCap;
	
	@ManyToMany 
	private List<Lecturer> lecturers;
	
	@OneToMany (mappedBy="courseID")
	private List<Student_Course> classList;
	
}
