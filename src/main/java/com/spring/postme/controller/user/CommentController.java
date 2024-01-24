package com.spring.postme.controller.user;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.postme.model.Comment;
import com.spring.postme.service.user.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentservice;
	
	// 댓글 출력
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String AllComment(Model model) {
		// post의 id = comments의 postid 일때
		
		List<Comment> commentList = commentservice.getAllCommentList();
		model.addAttribute("CommentList", commentList);
		
		
		return "main";
	}
	
	// 댓글작성
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String insertCommentForm() {
		return "main";
	}
	
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String insertComment(@ModelAttribute Comment newComment) {
		
		String view = "error";
		
		boolean CommentResult = false;
		
		try {
			CommentResult = commentservice.insertComment(newComment);
			if(CommentResult) {
				view = "main";
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return view;
	}
	
	
	
	// 수정
	@RequestMapping(value = "/comm", method = RequestMethod.GET)
	public String updateCommentForm(@PathVariable Integer userId, Model model) throws Exception {
		
		Comment comment = commentservice.getCommentByuserId(userId);
		model.addAttribute("comment", comment);
		
		
		return "main";
	}
	
	
	@RequestMapping(value = "/comm/{userId}", method = RequestMethod.PUT)
	public String updateComment(@PathVariable Integer userId,
							@ModelAttribute Comment newComment) {
		String view = "main";
		
		Comment comment = null;
		boolean result = false;
		
		try {
			comment = commentservice.getCommentByuserId(userId);
			
			comment.setContent(newComment.getContent());
			
			result = commentservice.updateComment(comment);
			
			if(result) {
				view = "redirect:/comm/" + userId;
				return view;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return view;
		}
		
		
		return view;
	}
	
	
	
	// 댓글 삭제
	@RequestMapping(value = "/comm/{userId}", method = RequestMethod.DELETE)
	public String deleteComment(@PathVariable Integer userId) throws SQLException, Exception {
		String view = "main";
		boolean result = false;
		
		result = commentservice.deleteComment(userId);
		
		if(result) {
			view = "redirect:/comm/";
			return view;
		}
		return view;
	}
	
	
}