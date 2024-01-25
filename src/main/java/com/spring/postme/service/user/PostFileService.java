package com.spring.postme.service.user;

import java.io.File;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.postme.mapper.PostFileMapper;
import com.spring.postme.model.PostFile;
import com.spring.postme.service.impl.PostFileServiceImpl;

@Service
public class PostFileService implements PostFileServiceImpl {
	@Autowired
	PostFileMapper attachmentFileMapper;

	// FileId로 file 출력
	public PostFile getAttachmentFileByFileId(int fileId) throws SQLException, Exception {
		PostFile attachmentFile = null;
		attachmentFile = attachmentFileMapper.getAttachmentFileByFileId(fileId);
//		System.out.println(attachmentFile);
		return attachmentFile;
	}

	// postId로 file 찾기
	public PostFile getAttachmentFileByPostId(int postId) {
		PostFile attachmentFile = null;

		attachmentFile = attachmentFileMapper.getAttachmentFileByPostId(postId);

		return attachmentFile;
	}

	// insert
	public boolean insertAttachmentFile(MultipartFile file, int postId) {
		boolean result = false;
		try {
			if (file.isEmpty()) {
				return false;
			}
			// 실행자 파일경로로 수정필요
			String filePath = "C:\\Users\\user\\Desktop\\Boardproject1\\filetest";
			String attachmentOriginalFileName = file.getOriginalFilename();
			Long attachmentFileSize = file.getSize();

			PostFile attachmentFile = PostFile.builder().postId(postId).filename(attachmentOriginalFileName)
					.filesize(attachmentFileSize).filpath(filePath).build();

			int res = attachmentFileMapper.insertAttachmentFile(attachmentFile);

			if (res != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean deleteAttachmentFileByFileNo(int fileId) throws Exception {
		boolean result = false;
		PostFile file = null;

		file = getAttachmentFileByFileId(fileId);

		// 로컬 서버 파일 삭제
		File serverFile = new File(file.getFilepath() + "\\" + file.getFilename());

		boolean serverDeleteResult = serverFile.delete();

//		System.out.println("serverDeleteResult : " + serverDeleteResult);
		// db 삭제
		int res = attachmentFileMapper.deleteAttachmentFileByFileId(fileId);
//		System.out.println("res : " + res);

		if (serverDeleteResult && res != 0) {
			result = true;
		}

		return result;
	}

}
