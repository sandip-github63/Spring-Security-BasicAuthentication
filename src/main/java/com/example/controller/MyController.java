package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserRequest;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.RoleService;
import com.example.service.UserService;

@RestController
@RequestMapping("/Admin")
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService rolService;
	
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createUserHandler(@RequestBody UserRequest uRequest) {
		
		//role 
		Role role=new Role();
		if(uRequest.getRole()!=null) {
			   Optional<Role> r = this.rolService.getRoleByRoleName(uRequest.getRole());
			   if(r.isPresent()) {				   
			       role=r.get();     	
			 }
		}
		
		User user=new User();
		
		if(uRequest.getUserName()!=null && uRequest.getPassword()!=null) {
			 user.setUserName(uRequest.getUserName());
			 user.setPassword(uRequest.getPassword());
		}
		
		ArrayList<UserRole> list = new ArrayList<UserRole>();
		
		UserRole userRole = new UserRole();
		
		userRole.setUser(user);
		userRole.setRole(role);
		
		list.add(userRole);
		
		user.setuRole(list);
		
		Optional<User> u = this.userService.createNewUser(user);
		if(u.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid details");
						
		}
	}
	
	@GetMapping("/hello")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> helloWolrd(){
		return ResponseEntity.status(HttpStatus.OK).body("working fine");
	}
	
	
	@GetMapping("/welcome")
	public ResponseEntity<?> welcomePage(){
		return ResponseEntity.status(HttpStatus.OK).body("Welcome page is here..");
	}
	

}
