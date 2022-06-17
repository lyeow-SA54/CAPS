package sg.edu.iss.team5.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="lecturer")
@Data
@NoArgsConstructor

public class Lecturer {
	
	@Id
	private String lecturerID;
	private String name;
	private String email;
	@ManyToMany (mappedBy="lecturers")
	private List<Course> teachings;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
}
