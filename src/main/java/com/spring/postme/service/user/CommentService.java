package com.spring.postme.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.postme.model.Comment;
import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.service.impl.CommentServiceImpl;

@Service
public class CommentService implements CommentServiceImpl {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentMapper.findCommentsByPostId(postId);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentMapper.findCommentById(id);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentMapper.deleteComment(id);
    }
}
