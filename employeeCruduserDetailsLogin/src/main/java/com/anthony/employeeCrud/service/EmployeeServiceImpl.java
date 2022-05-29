package com.anthony.employeeCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anthony.employeeCrud.dao.EmployeeRepository;
import com.anthony.employeeCrud.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	/*
	 * //*Two qualifiers that could be used before we switched to Spring Data JPA
	 * 
	 * @Qualifier("employeeDAOJPAImpl")
	 * 
	 * @Qualifier("employeeDAOImpl") private EmployeeDAO employeeDAO;
	 *
	 * @Autowired public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
	 * this.employeeDAO = employeeDAO; }
	 */

	private EmployeeRepository employeeRepository;

	@Autowired // Gives CRUD functionality w/o having to have an implementation class
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override // @Transactional not needed when using JpaRepository, because it is provided by it
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastName();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		Employee employee = null;
		if (result.isPresent())
			employee = result.get();
		else
			throw new RuntimeException("Employee id: " + id + " does not exist.");
		return employee;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> searchBy(String nameOrEmail) {

		List<Employee> employees;
		if (nameOrEmail != null && nameOrEmail.trim().length() > 0)
			employees = employeeRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(nameOrEmail, nameOrEmail, nameOrEmail);
		else
			employees = employeeRepository.findAllByOrderByLastName();

		return employees;
	}

}
