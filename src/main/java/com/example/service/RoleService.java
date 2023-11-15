package com.example.service;

import java.util.Optional;

import com.example.model.Role;

public interface RoleService {
	
	public Optional<Role> getRoleByRoleName(String roleName);

}
