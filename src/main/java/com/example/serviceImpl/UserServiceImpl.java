package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<User> createNewUser(User user) {
		
		 user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		 this.roleRepository.save(user.getuRole().get(0).getRole());
		  
		 User f = this.userRepository.save(user);
		 
		 if(f!=null) {
			 return Optional.of(f);
		 }
		 
		 return Optional.empty();
	}

	

}
