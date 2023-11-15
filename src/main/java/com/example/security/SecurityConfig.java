package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.example.filter.MyFilter;


@Configuration
@EnableWebSecurity //by this annotation you are telling to spring security to please disable default configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	//Authentication configuration Started.
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration  config) throws Exception {
		System.out.println("AuthenticationManager object created...");
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());
		System.out.println("AuthenticationProvider object created...");
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("BCrypPasswrodEncoder object created...");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {	
		
		ChildUserDetailsService childUserDetailsService = new ChildUserDetailsService();
		System.out.println("UserDetialsService object created...");
		return childUserDetailsService;
	}
	
	
	//Authorization code start
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return  http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/Authenticate/","/Admin/welcome").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/Admin/**").authenticated().and().httpBasic().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(new MyFilter(),UsernamePasswordAuthenticationFilter.class)
				.build();		
	}

}
