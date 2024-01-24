package com.spring.postme.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
	
	private Integer id;
	private Integer postId;
	private Integer userId;
	private String content;
	private LocalDateTime createdAt; 
	private LocalDateTime modifiedAt;
	
	@Builder
	public Comment(int id, int postId, int userId, String content, LocalDateTime createdAt,
			LocalDateTime modifiedAt) {
		super();
		this.id = id;
		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	
	
	
}