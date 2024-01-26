package com.spring.postme.service.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.postme.mapper.CommentMapper;
import com.spring.postme.mapper.PostMapper;
import com.spring.postme.mapper.UserMapper;
import com.spring.postme.model.Comment;
import com.spring.postme.model.Post;
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

	public void updateUserAdminStatus(Integer userId, boolean adminStatus) {
		userMapper.updateUserAdminStatus(userId, adminStatus);
	}

	public void deleteUserByUserId(Integer userId) {
		userMapper.deleteUserById(userId);
	}

	public List<Post> getPostList() {
		return postMapper.findAll();
	}

	public void deletePostById(Integer postId) {
		postMapper.deleteByPostId(postId);
	}

	public void deleteCommentByPostId(Integer postId) {
		commentMapper.deleteByPostId(postId);
	}

	@Transactional
	public void insertSampleData() {
		for (int i = 1; i <= 100; i++) {
			User user;
			String hashedPassword;

			if (i == 1) {
				hashedPassword = BCrypt.hashpw("admin", BCrypt.gensalt());
				user = new User(null, "admin", hashedPassword, "Admin User", "admin@example.com", true,
						LocalDateTime.now());
				userMapper.insertAdmin(user);
			} else {
				hashedPassword = BCrypt.hashpw("password" + i, BCrypt.gensalt());
				user = new User(null, "user" + i, hashedPassword, "User" + i, "user" + i + "@example.com", false,
						LocalDateTime.now());
				userMapper.insertUser(user);
			}
		}

		for (int i = 1; i <= 100; i++) {
			Post post = Post.builder().userId(i).title("Sample Title " + i).content("Sample content for post " + i)
					.createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build();
			postMapper.insertPost(post);
		}

		for (int i = 1; i <= 100; i++) {
			Comment comment = new Comment(null, i, i, "Sample comment " + i, LocalDateTime.now(), LocalDateTime.now());
			commentMapper.insertComment(comment);
		}
	}

}
