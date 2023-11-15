package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface UserService {
	
	public Optional<User> createNewUser(User user);
	
	

}
