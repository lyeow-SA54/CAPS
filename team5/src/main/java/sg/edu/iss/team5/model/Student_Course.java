package sg.edu.iss.team5.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.team5.helper.status;

@Entity
@Table(name="Student_Course")
@Data
@NoArgsConstructor

public class Student_Course {
	
	@Id
	@Column(name = "sc_ID")
    private String sc_ID;
	
	@ManyToOne
	@JoinColumn(name = "sc_studentID")
	private Student studentID;
	@ManyToOne
	@JoinColumn(name = "sc_courseID")
	private Course courseID;
	@Basic
	@Column(name = "score")
	private int score;
	@Column(name = "status_type", columnDefinition = "ENUM('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRADUATED')")
	@Enumerated(EnumType.STRING)
	private status eventType;

	public Student_Course(Student student, Course course, int score, status event)
	{
		studentID = student;
		courseID = course;
		this.score=score;
		eventType = event;
	}
}
