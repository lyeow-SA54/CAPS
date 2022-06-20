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

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Course {
	public Course(String code, String name, int courseDays) {}
	public Course(String code, String name, int courseDays, LocalDate startDate, String description, String room,
			double credits, String lessonDay, double fee, int maxCap) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "course_id")
	private String courseID;
	@Basic
	@Column(name = "code")
	private String code;
	@Basic
	@Column(name = "course_name")
	private String name;
	@Basic
	@Column(name = "course_days")
	private int courseDays;
	
	@Basic
	@Column(name = "start_date")
	  @DateTimeFormat(pattern = "dd-MM-yyyy") 
	  private LocalDate startDate; 
	@Basic
	@Column(name = "description")
	  private String description;
	@Basic
	@Column(name = "room")
	  private String room;
	@Basic
	@Column(name = "credits")
	  private double credits; 
	@Basic
	@Column(name = "lesson_day")
	  private String lessonDay; 
	@Basic
	@Column(name = "fee")
	  private double fee; 
	  @Basic
		@Column(name = "class_pax")
	  private int classPax; 
	  @Basic
		@Column(name = "max_cap")
	  private int maxCap;
	 
	@ManyToMany(mappedBy = "teachings", cascade = CascadeType.ALL) 
	private Set<Lecturer> lecturers;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy="courseID", cascade = CascadeType.ALL)
	private Set<Student_Course> classList;
	
}
