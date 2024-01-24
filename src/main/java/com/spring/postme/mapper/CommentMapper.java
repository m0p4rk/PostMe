package com.spring.postme.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.postme.model.Comment;

@Mapper
public interface CommentMapper {

	public List<Comment> getAllCommentList ();

	public int insertComment(Comment newComment) throws SQLException;

	public Comment getCommentByuserId(Integer userId) throws SQLException;

	public int updateComment(Comment comment) throws SQLException;

	public int deleteComment(Integer userId) throws SQLException;


	
	
	
	
	
	
}