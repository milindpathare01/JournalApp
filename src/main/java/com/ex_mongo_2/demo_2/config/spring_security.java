package com.ex_mongo_2.demo_2.config;

import javax.servlet.Filter;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ex_mongo_2.demo_2.jwtFilter.jwtFilter;
import com.ex_mongo_2.demo_2.service.CustomeUserDetailServices;

@Configuration
@EnableWebSecurity
public class spring_security extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomeUserDetailServices customeUserDetailServices;
	
	@Autowired
	private jwtFilter filter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customeUserDetailServices).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/journal_connection/**","/new_user/**")
		.authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll()
		.and()
		.httpBasic();
		
		//http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

}
