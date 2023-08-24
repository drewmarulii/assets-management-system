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
import com.lawencon.assetsystem.dto.assettype.AssetTypeInsertReqDto;
import com.lawencon.assetsystem.dto.assettype.AssetTypeResDto;
import com.lawencon.assetsystem.service.AssetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("assettypes")
@SecurityRequirement(name = "bearerAuth")
public class AssetTypeController {

	@Autowired
	private AssetService assetService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody AssetTypeInsertReqDto type) {
		final InsertResDto result = assetService.insertAssetType(type);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AssetTypeResDto>> getAll() {
		final List<AssetTypeResDto> types = assetService.getAllType();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
}
