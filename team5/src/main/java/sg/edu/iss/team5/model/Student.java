package sg.edu.iss.team5.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sg.edu.iss.team5.helper.status;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor

public class Student {
	
	@Id
	@Column(name = "student_id")
	private String studentID;
	@Basic
	@Column(name = "student_name")
	private String name;
	@Basic
	@Column(name = "student_email")
	private String email;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Basic
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "status_type", columnDefinition = "ENUM('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs')")
	@Enumerated(EnumType.STRING)
	private status eventType;
	@Basic
	@Column(name = "GPA")
	private double gpa;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="studentID",cascade = CascadeType.ALL, orphanRemoval = true)	
	private Set<Student_Course> studyList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Student (String id, String name, String email)
	{
		this.studentID = id;
		this.name = name;
		this.email = email;
		Role role = new Role("STUDENT");
		List<Role> rolelist = List.of(role);
		this.user  = new User(id, rolelist);
	}
}
