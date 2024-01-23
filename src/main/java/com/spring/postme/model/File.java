package com.spring.postme.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class File {
		private Integer id;
		private Integer userId;
		private Integer postId;
		private String filename;
		private String filepath;
		private long filesize;
		
		@Builder
		public File(Integer id, Integer userId, Integer postId, String filename, String filpath, long filesize) {
			this.id = id;
			this.userId = userId;
			this.postId = postId;
			this.filename = filename;
			this.filepath = filpath;
			this.filesize = filesize;
	}
}
