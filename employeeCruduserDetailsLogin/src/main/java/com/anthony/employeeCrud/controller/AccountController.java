package com.anthony.employeeCrud.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anthony.employeeCrud.dto.UserDTO;
import com.anthony.employeeCrud.entity.User;
import com.anthony.employeeCrud.service.UserService;

@Controller
@RequestMapping("account")
public class AccountController {

	private final UserService userService;
	private final Logger logger;

	@Autowired
	public AccountController(UserService userService) {
		this.userService = userService;
		this.logger = Logger.getLogger(getClass().getName());
	}

	@GetMapping("login")
	public String loginForm() {
		return "login-form";
	}

	@GetMapping("access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}

	@GetMapping("register")
	public String registerForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "register-form";
	}

	@PostMapping("processRegistration")
	public String registerForm(@ModelAttribute("userDTO") UserDTO userDTO, Model model, BindingResult bindingResult) {

		// Check for user in db
		User existingUsername = userService.findUserByUsername(userDTO.getUsername());
		User existingEmail = userService.findUserByEmail(userDTO.getEmail());
		// If user != null then username is already taken
		if (existingUsername != null) {
			System.out.println(existingUsername);
			model.addAttribute("userDTO", userDTO);
			logger.info("Username already in use");
			return "register-form";
		}
		if (existingEmail != null) {
			System.out.println(existingEmail);
			model.addAttribute("userDTO", userDTO);
			logger.info("Email already in use");
			return "register-form";
		}
		userService.save(userDTO);
        logger.info("Successfully created user: " + userDTO.getUsername());
		
		return "login-form";

	}
}
