package com.spring.postme.mapper;

import com.spring.postme.model.File;

public interface FileMapper {

	File getAttachmentFileByFileId(int fileId);

	int insertAttachmentFile(File attachmentFile);

	int deleteAttachmentFileByFileId(int fileId);

}
