package com.spring.postme.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.mapper.PostMapper;
import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.User;
import com.spring.postme.service.impl.AdminServiceImpl;

@Service
public class AdminService implements AdminServiceImpl {
	
	private final PostMapper postMapper;
	private final CommentMapper commentMapper;
	private final UserMapper userMapper;
	
	@Autowired
	public AdminService(PostMapper postMapper, CommentMapper commentMapper, UserMapper userMapper) {
		this.postMapper = postMapper;
		this.commentMapper = commentMapper;
		this.userMapper = userMapper;
	}

	@Override
	public int countUsers() {
		return userMapper.countUsers();
	}
	
	@Override
	public void deleteAllUsers() {
		userMapper.deleteAllUsers();
	}

	@Override
	public int countPosts() {
		return postMapper.countPosts();
	}
	
	@Override
	public void deleteAllPosts() {
		postMapper.deleteAllPosts();
	}

	@Override
	public int countComments() {
		return commentMapper.countComments();
	}
	
	@Override
	public void deleteAllComments() {
		commentMapper.deleteAllComments();
	}
	
	@Transactional
    public void deleteAllData() {
        commentMapper.deleteAllComments();
        postMapper.deleteAllPosts();
        userMapper.deleteAllUsers();
    }

    public List<User> getUserList() {
        return userMapper.getUserList();
    }
    
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }
    
    
    public void editUser(User user) {
        userMapper.updateUser(user);
    }

}
