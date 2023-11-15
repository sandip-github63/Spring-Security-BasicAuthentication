package com.example.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.Role;
import com.example.model.User;

public class ChildUserDetails implements UserDetails {
	
	private String userName;
	
	private String password;
	
	private Role role;
	
	public ChildUserDetails(User user){
		
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.role=user.getuRole().get(0).getRole();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority(role.getRole()));
		
		return list;
	}

	@Override
	public String getPassword() {		
		return this.password;
	}

	@Override
	public String getUsername() {		
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
