package com.lawencon.assetsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.asset.AssetGeneralResDto;
import com.lawencon.assetsystem.dto.asset.AssetInsertReqDto;
import com.lawencon.assetsystem.dto.asset.AssetResDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateReqDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateStatusReqDto;
import com.lawencon.assetsystem.service.AssetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("assets")
@SecurityRequirement(name = "bearerAuth")
public class AssetController {

	@Autowired
	private AssetService assetService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody AssetInsertReqDto asset) {
		final InsertResDto result = assetService.insert(asset);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AssetResDto>> getAll() {
		final List<AssetResDto> assets = assetService.getAll();
		return new ResponseEntity<>(assets, HttpStatus.OK);
	}
	
	@GetMapping("/byId")
	public ResponseEntity<AssetResDto> getById(@RequestParam("code") long id) {
		final AssetResDto asset = assetService.getByID(id);
		return new ResponseEntity<>(asset, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<AssetResDto>> getByType(@RequestParam("code") int type) {
		
		final List<AssetResDto> assets = assetService.getItemByType(type);
		return new ResponseEntity<>(assets, HttpStatus.OK);
	}
	
	@GetMapping("/general")
	public ResponseEntity<List<AssetGeneralResDto>> getAllGeneral() {
		final List<AssetGeneralResDto> assets = assetService.getAssetGeneral();
		return new ResponseEntity<>(assets, HttpStatus.OK);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<UpdateResDto> updateAsset(@RequestBody AssetUpdateReqDto asset) {
		final UpdateResDto result = assetService.update(asset);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateAssetStatus(@RequestBody AssetUpdateStatusReqDto asset) {
		final UpdateResDto result = assetService.updateStatus(asset);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
