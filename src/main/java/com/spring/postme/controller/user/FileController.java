package com.spring.postme.controller.user;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.postme.model.File;
import com.spring.postme.service.user.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping(value = "/download/file/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {
		
		File attachmentFile = null;
		Resource resource = null;
		try {
			attachmentFile = fileService.getAttachmentFileByFileId(fileId);
//			System.out.println(attachmentFile);
			Path path = Paths.get(attachmentFile.getFilepath() + "\\" + attachmentFile.getFilename());
			resource = new InputStreamResource(Files.newInputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition
											.builder("attachment")
											.filename(attachmentFile.getFilename())
											.build());
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	
}
