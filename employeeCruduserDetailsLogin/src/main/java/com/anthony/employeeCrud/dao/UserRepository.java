package com.anthony.employeeCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.employeeCrud.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// Basic CRUD methods given for free by extending JpaRepository interface with
	// our own, no additional code needed
	User findByUsername(String username);

	User findByEmail(String email);
}
