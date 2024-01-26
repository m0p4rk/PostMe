package com.spring.postme.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.postme.model.Post;
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

	@GetMapping("/posts")
	public String getPostList(Model model) {
		List<Post> postList = adminService.getPostList();
		model.addAttribute("postsList", postList);
		return "AdminPost";
	}

	@PostMapping("/users/promote/{userId}")
	public String promoteUserToAdmin(@PathVariable Integer userId, @RequestParam("adminStatus") boolean adminStatus,
			RedirectAttributes redirectAttributes) {
		adminService.updateUserAdminStatus(userId, adminStatus);
		redirectAttributes.addFlashAttribute("message", "사용자의 관리자 상태가 변경되었습니다.");
		return "redirect:/admin/users";
	}

	@PostMapping("/users/update/{userId}")
	public String updateUser(@PathVariable Integer userId, @RequestParam("name") String name,
			@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		User user = adminService.getUserById(userId);
		user.setName(name);
		user.setEmail(email);
		adminService.editUser(user);
		redirectAttributes.addFlashAttribute("successMessage", "사용자 정보가 업데이트되었습니다.");
		return "redirect:/admin/users";
	}

	@PostMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		adminService.deleteUserByUserId(userId);
		return "redirect:/admin/users";
	}

	@PostMapping("/posts/delete/{postId}")
	public String deletePost(@PathVariable Integer postId) {
		adminService.deleteCommentByPostId(postId);
		adminService.deletePostById(postId);
		return "redirect:/admin/posts";
	}

	@PostMapping("/deleteAllData")
	public String deleteAllData(RedirectAttributes redirectAttributes) {
		adminService.deleteAllData();
		redirectAttributes.addFlashAttribute("message", "모든 데이터가 삭제되었습니다.");
		return "redirect:/admin/dashboard";
	}

	@PostMapping("/insertSampleData")
	public String insertSampleData(RedirectAttributes redirectAttributes) {
		adminService.insertSampleData();
		redirectAttributes.addFlashAttribute("message", "샘플 데이터가 추가되었습니다.");
		return "redirect:/admin/settings";
	}

}