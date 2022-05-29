package com.anthony.employeeCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

	@GetMapping("login")
	public String loginForm() {
		return "login-form";
	}
	
	@GetMapping("access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
}
