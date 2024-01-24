package com.spring.postme.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.postme.model.Comment;
import com.spring.postme.model.Post;
import com.spring.postme.model.PostFile;
import com.spring.postme.service.user.CommentService;
import com.spring.postme.service.user.PostFileService;

import com.spring.postme.service.user.PostService;

@Controller
public class PostController {

	private final PostService postService;
	private final CommentService commentService;
	private final PostFileService postFileService;
	
	private static final int POSTS_PER_PAGE = 6;

	@Autowired
	public PostController(PostService postService, CommentService commentService, PostFileService postFileService) {
		this.postService = postService;
		this.commentService = commentService;
		this.postFileService = postFileService;
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
		
		PostFile fileInfo = postFileService.getAttachmentFileByPostId(id);
		
		
		model.addAttribute("fileInfo", fileInfo);
		model.addAttribute("post", post);
		model.addAttribute("commentsList", comments);

		return "postDetail";
	}
	
	@PostMapping("/posts/add")
	public String savePosts(MultipartFile file, @ModelAttribute("post") Post post, HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		post.setUserId(userId);
		System.out.println(userId);
		postService.savePost(post);
		System.out.println(post.getId());
		System.out.println(file.getName());
		if(file.getOriginalFilename() != null) {
			postFileService.insertAttachmentFile(file, post.getId());
		}
		
		
		
		return "redirect:/";
	}
	

	
//	@PostMapping("/posts/add")
//	public String addPost(@RequestParam("title") String title, @RequestParam("content") String content,
//			@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {
//
//		Integer userId = (Integer) session.getAttribute("loggedInUserId");
//
//		// 새로운 Post 객체 생성
//		Post post = new Post();
//		post.setTitle(title);
//		post.setContent(content);
//		post.setUserId(userId);
//
//		// 파일 처리 로직 (파일이 있는 경우)
//		if (file != null && !file.isEmpty()) {
//			// 파일 처리 로직 구현
//		}
//
//		// 게시글 저장
//		postService.savePost(post);
//
//		return "redirect:/";
//	}

	@GetMapping("/posts/delete/{postId}")
	public String deletePost(@PathVariable("postId") Integer postId) {
		// 게시글 삭제 로직 구현
		postService.deletePost(postId);

		// 삭제 후 메인 페이지로 리디렉션
		return "redirect:/";
	}
	
	@PostMapping("/posts/update/{id}")
    public String updatePost(@PathVariable("id") Integer id, 
                             @ModelAttribute Post post, 
                             RedirectAttributes redirectAttributes,HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("loggedInUserId");
        // 게시글의 ID를 설정
        post.setId(id);
        post.setUserId(userId);
        // 게시글 업데이트 로직 수행
        postService.updatePost(post);

        // 수정 후 리다이렉트 시 메시지 전달
        redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다.");

        // 게시글 상세 페이지로 리다이렉트
        return "redirect:/posts/" + id;
    }


}
