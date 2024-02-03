package com.greatLearning.studentManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.studentManagement.service.UserDetailsServiceImpl;




@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	private UserDetailsService userDetailService() {
		return new UserDetailsServiceImpl();
	}



	

	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 
	 
		

		protected void confugure(AuthenticationManagerBuilder auth) throws Exception{
			
			 auth.authenticationProvider(authenticationProvider());
				}
	 
	

	protected void confugure(HttpSecurity http) throws Exception{
		 http.authorizeRequests()
.antMatchers("/","/student/save","/student/showFormForAdd","/student/403").hasAnyAuthority("USER","ADMIN")
.antMatchers("/student/showFormForUpdate","student/delete").hasAuthority("ADMIN")
.anyRequest()
.authenticated()	
.and()
.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/student/list").permitAll()
.and()
.logout().logoutSuccessUrl("/login").permitAll()
.and()
.exceptionHandling().accessDeniedPage("/student/403")
.and()
.cors().and().csrf()
.disable();
	 }
		
}
