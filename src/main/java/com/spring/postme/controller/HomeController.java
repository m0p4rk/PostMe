package com.spring.postme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/dashboard")
	public String showPostDashboard() {
		return "postDashboard";
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
}
