package com.spring.postme.service.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.postme.mapper.FileMapper;
import com.spring.postme.model.File;

@Service
public class FileService {
	@Autowired
	FileMapper attachmentFileMapper;
	
	// 객체
	public File getAttachmentFileByFileId(int fileId) throws SQLException, Exception { 
		File attachmentFile = null;
		
		attachmentFile = attachmentFileMapper.getAttachmentFileByFileId(fileId);
		System.out.println(attachmentFile);
		return attachmentFile;
	}
}
