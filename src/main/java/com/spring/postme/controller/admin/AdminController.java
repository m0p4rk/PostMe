package com.spring.postme.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @PostMapping("/deleteAllData")
    public String deleteAllData(RedirectAttributes redirectAttributes) {
        adminService.deleteAllData();
        redirectAttributes.addFlashAttribute("message", "모든 데이터가 삭제되었습니다.");
        return "redirect:/admin/dashboard";
    }

}

