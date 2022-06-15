package sg.edu.iss.team5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="admin")
@Data
@NoArgsConstructor
public class Admin {
	
	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id-generator")
//    @GenericGenerator(name = "id-generator", 
//	parameters = {
//            @Parameter(name = IdGenerator.INCREMENT_PARAM, value = "50"),
//            @Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
//            @Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") }, 
//      strategy = "sg.edu.iss.team5.model.IdGenerator")
	private int userId;
	private String name;
	
	public Admin(String name) {
		this.name = name;
	}

}
