package com.spring.postme.mapper;

import com.spring.postme.model.Post;
import java.util.List;
import java.util.Map;

public interface PostMapper {

	List<Post> findAll();

	Post findById(Integer id);

	void insertPost(Post post);

	void updatePost(Post post);

	void deleteById(Integer id);

	List<Post> findPostsByPage(Map<String, Object> params);

	int countPosts();

}
