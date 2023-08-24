package com.lawencon.assetsystem.controller;

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
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusInsertReqDto;
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusResDto;
import com.lawencon.assetsystem.service.AssetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("assetstatus")
@SecurityRequirement(name = "bearerAuth")
public class AssetStatusController {

	@Autowired
	private AssetService assetService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody AssetStatusInsertReqDto status) {
		final InsertResDto result = assetService.insertAssetStatus(status);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AssetStatusResDto>> getAll() {
		final List<AssetStatusResDto> status = assetService.getAllStatus();
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}
