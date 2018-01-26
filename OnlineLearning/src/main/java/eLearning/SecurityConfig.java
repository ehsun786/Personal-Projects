package eLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
            .ignoring()
            .antMatchers("/resources/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
    		 .antMatchers("/register","/register1","/registerLecturer", "/forgotPassword","/resetPassword","/auth/*", "/contactUs").permitAll()
				.antMatchers("lecturer/**")
					.hasRole("LECTURER")
				.antMatchers("learner/**")
					.hasRole("LEARNER")
				.antMatchers("admin/**")
					.hasRole("ADMINISTRATOR")
    			.anyRequest().authenticated()
    			.and()
		    .formLogin()
				.loginPage("/auth/user-login")  //login page location
				.defaultSuccessUrl("/auth/success-login",true) // the second parameter is for enforcing this url always
				.loginProcessingUrl("/login")
				.failureUrl("/auth/error-login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
		        .and()
		    .requiresChannel()
		    	.anyRequest()
		    	.requiresSecure()
    			.and()
   			.exceptionHandling().accessDeniedPage("/auth/access-denied");
    }
    

	@Autowired 
	private UserDetailsService userDetailsService; 	

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(pe);	
	}
    
}
	