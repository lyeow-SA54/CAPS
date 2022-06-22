package sg.edu.iss.team5.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  	//@formatter:off
	  http.csrf().disable()
	  	.authorizeRequests()
        .antMatchers("/login").permitAll()
//        .antMatchers("/**").hasAnyAuthority("STUDENT", "ADMIN", "LECTURER")
        .antMatchers("/admin/**").hasAuthority("ADMIN")
        .antMatchers("/lecturer/**").hasAuthority("LECTURER")
        .antMatchers("/student/**").hasAuthority("STUDENT")
        .and()
        .formLogin().successHandler(successHandler)
		.and().logout().permitAll();
    //@formatter:on  
    }
    
	  @Override
	  public void configure(WebSecurity web) {
	    web.ignoring()
	        .antMatchers("/resources/**", "/static/**");
	  }
}