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
    
//    @GetMapping("/admin/dashboard")
//    public String showAdminMainPage() {
//    	return "AdminMain";
//    }
    
//    @GetMapping("/admin/users")
//    public String showAdminUserPage() {
//    	return "AdminUser";
//    }
//    
    @GetMapping("/admin/posts")
    public String showAdminPostPage() {
    	return "AdminPost";
    }
    
    @GetMapping("/admin/settings")
    public String showAdminSettingPage() {
    	return "AdminSetting";
    }
}
