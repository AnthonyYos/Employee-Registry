package com.anthony.employeeCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.employeeCrud.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	
	// Basic CRUD methods given for free by extending JpaRepository interface with our own, no additional code needed
	public Role findByRole(String role);
}
