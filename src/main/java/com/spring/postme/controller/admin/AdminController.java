package com.spring.postme.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.postme.model.User;
import com.spring.postme.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        int totalUsers = adminService.countUsers();
        int totalPosts = adminService.countPosts();
        int totalComments = adminService.countComments();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalPosts", totalPosts);
        model.addAttribute("totalComments", totalComments);

        return "AdminMain"; 
    }
    
    @GetMapping("/users")
    public String getUserList(Model model) {
        List<User> userList = adminService.getUserList();
        model.addAttribute("usersList", userList);
    	return "AdminUser";
    }
    
    @GetMapping("/users/edit/{userId}")
    public String editUser(@PathVariable Integer userId, Model model) {
        // 특정 사용자 정보 가져오기
        User user = adminService.getUserById(userId);

        if (user != null) {
            model.addAttribute("user", user);
            return "AdminEdit"; 
        } else {
            // 사용자를 찾을 수 없을 때의 처리
            return "AdminUser"; 
        }
    }
    // 사용자 정보 수정 처리
    @PostMapping("/admin/users/edit/confirm/{userId}")
    public String editUserConfirm(@PathVariable Integer userId, @ModelAttribute User user) {
        adminService.editUser(user);

        return "redirect:/users";
    }
    
    
    
    @PostMapping("/deleteAllData")
    public String deleteAllData(RedirectAttributes redirectAttributes) {
        adminService.deleteAllData();
        redirectAttributes.addFlashAttribute("message", "모든 데이터가 삭제되었습니다.");
        return "redirect:/admin/dashboard";
    }

}