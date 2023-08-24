package com.lawencon.assetsystem.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.provider.ProviderInsertReqDto;
import com.lawencon.assetsystem.dto.provider.ProviderResDto;
import com.lawencon.assetsystem.service.ProviderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("providers")
@SecurityRequirement(name = "bearerAuth")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertProvider(@RequestBody ProviderInsertReqDto provider) {
		final InsertResDto result = providerService.insert(provider);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProviderResDto>> getAll() throws SQLException {
		final List<ProviderResDto> providers = providerService.getAll();
		return new ResponseEntity<>(providers, HttpStatus.OK);
	}
}
