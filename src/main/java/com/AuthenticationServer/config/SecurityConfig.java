package com.AuthenticationServer.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.AuthenticationServer.service.UserService;





@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userDetailsService;
	
	@Bean
	public CustomPasswordEncoder bcrypPasswordEncoder() {
		return new CustomPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(bcrypPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf()
		.disable()
		.cors()
		.and()
		.authorizeRequests()
		.antMatchers("/authenticate","/validateToken","/hello","/api-docs").permitAll()
		.and()
		.authorizeRequests()
		.anyRequest().authenticated();
//		.and()
//		.addFilterBefore(jwtRequestFilters, UsernamePasswordAuthenticationFilter.class);
		
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
}

