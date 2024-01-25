package com.spring.postme.controller.user;

import javax.servlet.http.HttpServletResponse;
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
		if (user != null) {
			session.setAttribute("user", user.getUsername());
			session.setAttribute("loggedInUserId", user.getId());
			return "redirect:/";
		} else {
			return "redirect:/login?error=true";
		}
	}

	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session,  HttpServletResponse response) {
		session.invalidate();
		 //로그아웃 매핑이들어오면 캐싱 동작을 제어하는 헤더
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		 response.setHeader("Pragma", "no-cache");
		 response.setHeader("Expires", "0");
		return "redirect:login";
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