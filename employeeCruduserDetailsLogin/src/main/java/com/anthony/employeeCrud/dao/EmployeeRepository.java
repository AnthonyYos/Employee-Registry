package com.anthony.employeeCrud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.employeeCrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// Basic CRUD methods given for free by extending JpaRepository interface with our own, no additional code needed
	
	// Can create additional CRUD methods like find all and sort by lastname
	public List<Employee> findAllByOrderByLastName();
	
	public List<Employee> findByFirstNameContainsOrLastNameContainsOrEmailContains(String firstName, String lastName, String email);
}
