package com.spring.postme.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.postme.mapper.PostMapper;
import com.spring.postme.model.Post;
import com.spring.postme.service.impl.PostServiceImpl;

@Service
public class PostService implements PostServiceImpl {

	private final PostMapper postMapper;

	@Autowired
	public PostService(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	@Override
	public List<Post> findAll() {
		return postMapper.findAll();
	}

	@Override
	public Post findById(Integer id) {
		return postMapper.findById(id);
	}

	@Override
	public void savePost(Post post) {
		if (post.getId() == null) {
			postMapper.insertPost(post);
		} else {
			postMapper.updatePost(post);
		}
	}

	@Override
	public void updatePost(Integer id, Post post) {
		post.setId(id);
		postMapper.updatePost(post);
	}

	@Override
	public void deletePost(Integer id) {
		postMapper.deleteById(id);
	}

	public List<Post> findPostsByPage(int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("pageSize", pageSize);
		return postMapper.findPostsByPage(params);
	}

	@Override
	public int countPosts() {
		return postMapper.countPosts();
	}

}
