package sg.edu.iss.team5.model;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.team5.helper.PasswordEncoderConfig;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class User implements UserDetails{

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;
	@Column(name = "password")
	private String password;
	@OneToOne (mappedBy = "user")
	private Student studentuser;
	@OneToOne (mappedBy = "user")
	private Lecturer lectureruser;
	
	@CreatedBy
    private String createdBy;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    
	public User(String id, List<Role> roles) {
		this.username = id;
		this.roles = roles;
		this.password = new BCryptPasswordEncoder().encode("password");
	}
	
	public User(String Username, String Password, List<Role> roles)
	{
		this.username = Username;
		this.password = Password;
		this.roles = roles;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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
