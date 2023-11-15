package com.example.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "Role")
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	
	private String role;
	
	@OneToMany(mappedBy = "role" ,fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List<UserRole> uRole;
	
	public List<UserRole> getuRole() {
		return uRole;
	}

	public void setuRole(List<UserRole> uRole) {
		this.uRole = uRole;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + ", uRole=" + uRole + "]";
	}
	
	
	
	
}
