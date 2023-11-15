package com.example.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	
	@Override
	public Optional<Role> getRoleByRoleName(String roleName) {
		 Optional<Role> role = Optional.ofNullable(roleRepository.findByRole(roleName));
		 return role;
	}

}
