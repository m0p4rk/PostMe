package com.spring.postme.service.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.model.Comment;

@Service
public class CommentService {

	@Autowired
	CommentMapper commentmapper;

	//
	public List<Comment> getAllCommentList() {
		return commentmapper.getAllCommentList();
	}

	//
	public boolean insertComment(Comment newComment) throws SQLException {
		boolean result = false;
		
		int res = commentmapper.insertComment(newComment);
		if (res != 0) {
			result = true;
		} else {
			new Exception("댓글 작성 실패");
		}
		
		return result;
	}
	
	//
	public Comment getCommentByuserId(Integer userId) throws SQLException {
		return commentmapper.getCommentByuserId(userId);
	}

	//
	public boolean updateComment(Comment comment) throws SQLException {
		boolean result = false;
		
		int res = commentmapper.updateComment(comment);
		if (res != 0) {
			result = true;
		} else {
			new Exception("댓글 수정 실패");
		}
		
		
		return result;
	}

	public boolean deleteComment(Integer userId) throws SQLException {
		boolean result = false;
		
		int res = commentmapper.deleteComment(userId);
		
		if(res != 0) {
			result = true;
			return result;
		}
		
		return result;
	}
	
	
	
	
}
