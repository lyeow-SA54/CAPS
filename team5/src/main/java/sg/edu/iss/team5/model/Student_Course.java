package sg.edu.iss.team5.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
    private String sc_Id;
	@ManyToOne 
	private Student studentID;
	@ManyToOne 
	private Course courseID;
	private int score;
	@Column(name = "eventtype", columnDefinition = "ENUM('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs')")
	@Enumerated(EnumType.STRING)
	private status eventType;

}
