package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
@RequestMapping("/Authenticate")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> authenticateUser(@RequestBody User user){
		
		UsernamePasswordAuthenticationToken authentiation=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
		
		System.out.println(authentiation.toString());
		
		Authentication authenticate = this.authenticationManager.authenticate(authentiation);
		
		System.out.println("authenticated : "+authenticate.toString());
		
		if(authenticate.isAuthenticated()) {
			return ResponseEntity.status(HttpStatus.OK).body("Token generated successfully");
		}else {		
	       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid username and password");
		}
	}

}
