package com.lawencon.assetsystem.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.service.FileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("files")
@SecurityRequirement(name = "bearerAuth")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("{id}")
    public ResponseEntity<?> getFileById(@PathVariable("id") Long id) {
        final File file = fileService.getById(id);
        final String fileName = "attachment";
        final byte[] fileBytes = Base64.getDecoder().decode(file.getFile());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + file.getFileExtension())
                .body(fileBytes);
   }	
}
