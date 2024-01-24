package com.spring.postme.controller.user;

import com.spring.postme.model.Post;
import com.spring.postme.service.user.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

	private final PostService postService;
	private static final int POSTS_PER_PAGE = 6; // 페이지당 게시글 수

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/")
	public String listPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<Post> posts = postService.findPostsByPage(page, POSTS_PER_PAGE);
		int totalPosts = postService.countPosts();
		int totalPages = (int) Math.ceil((double) totalPosts / POSTS_PER_PAGE);

		model.addAttribute("posts", posts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "main"; // JSP 파일 이름
	}

}
