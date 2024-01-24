package com.spring.postme.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.postme.model.User;
import com.spring.postme.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String username, String password,
						HttpSession session) {
		String view = "error";
		User user = null;
		try {
			user = userService.getUserbyUsernameAndPassword(username, password);
			System.out.println(user);
			
			if (user != null)
					session.setAttribute("user", user.getUsername());
					session.setAttribute("user", user.getId());
					
					view = "redirect:/main";
					return view;
							
		}catch(Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/main";
	}
	
	
	// 회원가입
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String insertUser(@ModelAttribute User newUser) {
		String view = "error";
		boolean result = false;
		try {
			result = userService.insertUser(newUser);
			System.out.println(result);
			
			if(result) {
				view = "redirect:/main";
				return view;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return view;
		}
										
		return view;
	}
	
	
	
	
	
}