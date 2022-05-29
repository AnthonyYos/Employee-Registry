package com.anthony.employeeCrud.service;

import com.anthony.employeeCrud.dto.UserDTO;
import com.anthony.employeeCrud.entity.User;

public interface UserService   {
	User findUserByUsername(String username);

	User findUserByEmail(String email);
	
	void save(UserDTO userDTO);
}
