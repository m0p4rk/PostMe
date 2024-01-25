package com.spring.postme.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.spring.postme.model.User;
import com.spring.postme.service.user.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login.do")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		User user = userService.getUserByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("user", user.getUsername());
			session.setAttribute("loggedInUserId", user.getId());
			session.setAttribute("isAdmin", user.getIsAdmin());

			if (user.getIsAdmin()) {
				return "redirect:/admin/dashboard";
			} else {
				return "redirect:/";
			}
		} else {
			// 사용자 정보가 없거나 비밀번호가 틀린 경우
			return "redirect:/login?error=true";
		}
	}

	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 회원가입 처리
	@PostMapping("/register.do")
	public String register(@ModelAttribute User newUser) {
		boolean result = userService.insertUser(newUser);
		if (result) {
			return "redirect:/login";
		} else {
			return "redirect:/register?error=true";
		}
	}

}