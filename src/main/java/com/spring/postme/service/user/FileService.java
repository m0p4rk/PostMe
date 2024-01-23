package com.spring.postme.service.user;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.postme.mapper.FileMapper;
import com.spring.postme.model.File;

@Service
public class FileService {
	@Autowired
	FileMapper attachmentFileMapper;
	
	// FileId로 file 출력
	public File getAttachmentFileByFileId(int fileId) throws SQLException, Exception { 
		File attachmentFile = null;
		attachmentFile = attachmentFileMapper.getAttachmentFileByFileId(fileId);
		System.out.println(attachmentFile);
		return attachmentFile;
	}
	
	
	// insert
	public boolean insertAttachmentFile(MultipartFile file, int postId) {
		boolean result = false;
		try {
		if(file.isEmpty()) {
			return false;
		}
		// 실행자 파일경로로 수정필요
		String filePath = "C:\\Users\\user\\Desktop\\Boardproject1\\filetest";
		String attachmentOriginalFileName = file.getOriginalFilename();
//		UUID uuid = UUID.randomUUID();
//		String attachmentFileName = uuid.toString() + "_" + attachmentOriginalFileName;
		Long attachmentFileSize = file.getSize();
		
		File attachmentFile = File.builder()
									.postId(postId)
									.filename(attachmentOriginalFileName)
									.filesize(attachmentFileSize)
									.filpath(filePath)
									.build();
									
									
		int res = attachmentFileMapper.insertAttachmentFile(attachmentFile);
		
		if(res != 0) {
			file.transferTo(new java.io.File(filePath + "\\" + attachmentOriginalFileName));
			result = true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public boolean deleteAttachmentFileByFileNo(int fileId) throws Exception {
		System.out.println("contoller진입");
		boolean result = false;
		File file = null;
		
		file = getAttachmentFileByFileId(fileId);
		
		// 로컬 서버 파일 삭제
		java.io.File serverFile = new java.io.File(file.getFilepath() + "\\" + file.getFilename());
		
		boolean serverDeleteResult = serverFile.delete();
		
		System.out.println("serverDeleteResult : " + serverDeleteResult);
		// db 삭제
		int res = attachmentFileMapper.deleteAttachmentFileByFileId(fileId);
		System.out.println("res : " + res);
		
		if(serverDeleteResult && res != 0) {
			result = true;
		}
		
		return result;
	}
	
	
	
	
	
}
