package com.spring.postme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/dashboard")
	public String showPostDashboard() {
		return "postDashboard";
	}

    @GetMapping("/main")
    public String showMainPage() {
        return "main"; // 'views' 폴더 내의 'main.jsp'를 가리킵니다.
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
    	return "login";
    }
    
    @GetMapping("/register")
    public String showRegisterPage() {
    	return "register";
    }
    
    @GetMapping("/postdetail")
    public String showPostDetailPage(){
    	return "postDetail";
    }
}
