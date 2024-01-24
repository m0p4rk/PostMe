package com.spring.postme.controller.user;

import com.spring.postme.model.Comment;
import com.spring.postme.model.Post;
import com.spring.postme.service.user.CommentService;
import com.spring.postme.service.user.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

	private final PostService postService;
	private final CommentService commentService;
	private static final int POSTS_PER_PAGE = 6;

	@Autowired
	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String listPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<Post> posts = postService.findPostsByPage(page, POSTS_PER_PAGE);
		int totalPosts = postService.countPosts();
		int totalPages = (int) Math.ceil((double) totalPosts / POSTS_PER_PAGE);

		model.addAttribute("posts", posts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "main";
	}

	@GetMapping("/posts/{id}")
	public String showPostDetail(@PathVariable Integer id, Model model) {
		Post post = postService.findById(id);
		List<Comment> comments = commentService.getCommentsByPostId(id);

		model.addAttribute("post", post);
		model.addAttribute("commentsList", comments);

		return "postDetail";
	}

}
