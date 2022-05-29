package com.anthony.employeeCrud.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anthony.employeeCrud.dao.RoleRepository;
import com.anthony.employeeCrud.dao.UserRepository;
import com.anthony.employeeCrud.dto.UserDTO;
import com.anthony.employeeCrud.entity.Role;
import com.anthony.employeeCrud.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	private User populateUserData(UserDTO userDTO) {
		User user = new User();
		Role role = roleRepository.findByRole("ROLE_EMPLOYEE");
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setRoles(Arrays.asList(role));
		return user;
	}

	public void save(UserDTO userDTO) {
		User user = populateUserData(userDTO);
		userRepository.save(user);
	}

}
