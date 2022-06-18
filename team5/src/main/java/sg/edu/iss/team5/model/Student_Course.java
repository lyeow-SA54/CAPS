package sg.edu.iss.team5.model;

import javax.persistence.Basic;
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
	@Column(name = "stu_course_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int sc_Id;
	
	@ManyToOne 
	@JoinColumn(name = "student_id")
	private Student studentID;
	@ManyToOne 
	@JoinColumn(name = "course_id")
	private Course courseID;
	@Basic
	@Column(name = "score")
	private int score;
	@Column(name = "status_type", columnDefinition = "ENUM('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs')")
	@Enumerated(EnumType.STRING)
	private status eventType;

}
