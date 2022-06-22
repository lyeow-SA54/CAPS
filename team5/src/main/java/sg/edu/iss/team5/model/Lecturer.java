package sg.edu.iss.team5.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="lecturer")
@Data
@NoArgsConstructor

public class Lecturer {
	
	@Id
	@Column(name = "lecturer_id")
	private String lecturerID;
	@Basic
	@Column(name = "lecturer_name")
	private String name;
	@Basic
	@Column(name = "email")
	private String email;
	
	@ManyToMany 
	@JoinTable(name="lecturer_course",
	joinColumns = @JoinColumn(name = "lecturer_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Set<Course> teachings = new HashSet<Course>();
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	public Lecturer(String id)
	{
		this.lecturerID = id;
		Role role = new Role("LECTURER");
		List<Role> rolelist = List.of(role);
		this.user  = new User(id, rolelist);
	}
	
	public void removeCourse(Course course)
	{
		this.teachings.remove(course);
		course.getLecturers().remove(this);
	}
	
	public void addCourse(Course course)
	{
		this.teachings.add(course);
		course.getLecturers().add(this);
	}
}
