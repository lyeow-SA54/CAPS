package sg.edu.iss.team5.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import org.apache.commons.text.RandomStringGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.team5.helper.role;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class User{

	@Id
	@Column(name = "userid")
	private String userId;
	@NotEmpty
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private role role;
	@Column(name = "password")
	private String password;

	public User(String id, role role) {
		this.userId = id;
		this.role = role;

		this.password = generateCommonTextPassword();
	}
	
	public String generateCommonTextPassword() {
	    String pwString = generateRandomSpecialCharacters(5).concat(generateRandomAlphaNumeric(10));
	    List<Character> pwChars = pwString.chars()
	      .mapToObj(data -> (char) data)
	      .collect(Collectors.toList());
	    Collections.shuffle(pwChars);
	    String password = pwChars.stream()
	      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
	      .toString();
	    return password;
	}
	
	public String generateRandomSpecialCharacters(int length) {
	    RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
	        .build();
	    return pwdGenerator.generate(length);
	}
	
	public String generateRandomAlphaNumeric(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
		        .withinRange('0', 'z')
		        .filteredBy(LETTERS, DIGITS)
		        .build();
	    return pwdGenerator.generate(length);
	}

}
