package sg.edu.iss.team5.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
//        http.csrf()
//                .ignoringAntMatchers("/**")
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager()));
    	//@formatter:off
//  	  http.csrf().disable()
//  	  	.authorizeRequests()
//          .antMatchers("/login").permitAll()
////          .antMatchers("/**").hasAnyAuthority("STUDENT", "ADMIN", "LECTURER")
//          .antMatchers("/admin/**").hasAuthority("ADMIN")
//          .antMatchers("/lecturer/**").hasAuthority("LECTURER")
//          .and()
//          .formLogin().permitAll();
//      //@formatter:on
  	  
  	//@formatter:off
//	  http.csrf().disable()
//	  	.authorizeRequests()
//        .antMatchers("/login").permitAll()
//        .antMatchers("/**").hasAnyAuthority("STUDENT", "ADMIN", "LECTURER");
//        .antMatchers("/admin/**").hasAuthority("ADMIN")
//        .antMatchers("/lecturer/**").hasAuthority("LECTURER")
//        .antMatchers("/student/**").hasAuthority("STUDENT")
//        .and()
//        .formLogin().successHandler(successHandler)
//		.and().logout().permitAll();
    //@formatter:on
  	  
    }
}

//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("student").password(passwordEncoder().encode("password")).roles("STUDENT")
//        .and()
//        .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Override
//  protected void configure(final HttpSecurity http) throws Exception {
//
//    //@formatter:off
//	  http.csrf().disable()
//	  	.authorizeRequests()
//        .antMatchers("/login").permitAll()
//        .antMatchers("/**").hasAnyRole("USER", "ADMIN")
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .and()
//        .formLogin().permitAll();
//    //@formatter:on
//  }
//
//  @Override
//  public void configure(WebSecurity web) {
//    web.ignoring()
//        .antMatchers("/resources/**", "/static/**");
//  }
//
//}
///public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource(name = "customUserService")
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin().defaultSuccessUrl("/welcome", true);
//    }
//
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }
//}
