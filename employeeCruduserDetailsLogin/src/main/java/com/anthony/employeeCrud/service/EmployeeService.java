package com.anthony.employeeCrud.service;

import java.util.List;

import com.anthony.employeeCrud.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
	
	public List<Employee> searchBy(String nameOrEmail);

}
