package eMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
				.antMatchers("/product/**")
					.hasRole("MANAGER")
				.antMatchers("/order/**")
					.hasRole("DEVELOPER")
				.antMatchers("/item/**")
					.hasRole("DEVELOPER")
    			.anyRequest().authenticated()
    			.and()
		    .formLogin()
				.loginPage("/user-login")
				.defaultSuccessUrl("/success-login",true) // the second parameter is for enforcing this url always
				.loginProcessingUrl("/login")
				.failureUrl("/error-login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/user-login")
		        .and()
		    .requiresChannel()
		    	.anyRequest()
		    	.requiresSecure()
    			.and()
   			.exceptionHandling().accessDeniedPage("/access-denied");
    }

	@Autowired 
	private UserDetailsService userDetailsService; 	

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(pe);
		
	}
    
}
	