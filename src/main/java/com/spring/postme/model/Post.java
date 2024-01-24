package com.spring.postme.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private Integer id;
	private Integer userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
