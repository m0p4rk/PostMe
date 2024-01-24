package com.spring.postme.mapper;

import java.util.List;
import com.spring.postme.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	void insertComment(Comment comment);

	List<Comment> findCommentsByPostId(Integer postId);

	Comment findCommentById(Integer id);

	void updateComment(Comment comment);

	void deleteComment(Integer id);
}
