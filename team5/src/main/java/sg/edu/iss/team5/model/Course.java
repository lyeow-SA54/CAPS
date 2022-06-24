package sg.edu.iss.team5.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Course {

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
	  @DateTimeFormat(pattern = "yyyy-MM-dd") 
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
	 
	@ManyToMany(mappedBy = "teachings") 
	private Set<Lecturer> lecturers;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy="courseID")
	private Set<Student_Course> classList;
	
	public void removeLecturer(Lecturer lecturer)
	{
		this.lecturers.remove(lecturer);
		lecturer.getTeachings().remove(this);
	}
	
	public void addLecturer(Lecturer lecturer)
	{
		this.lecturers.add(lecturer);
		lecturer.getTeachings().add(this);
	}
	
	public Course(String courseId, String name, int courseDays) {
		this.courseID = courseId;
		this.name = name;
		this.courseDays = courseDays;
		}
}
