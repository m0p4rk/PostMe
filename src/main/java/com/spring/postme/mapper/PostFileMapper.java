package com.spring.postme.mapper;

import com.spring.postme.model.PostFile;

public interface PostFileMapper {

	PostFile getAttachmentFileByFileId(int fileId);

	int insertAttachmentFile(PostFile attachmentFile);

	int deleteAttachmentFileByFileId(int fileId);

	PostFile getAttachmentFileByPostId(int postId);

}
