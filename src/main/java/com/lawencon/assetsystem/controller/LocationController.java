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
import com.lawencon.assetsystem.dto.location.LocationInsertReqDto;
import com.lawencon.assetsystem.dto.location.LocationResDto;
import com.lawencon.assetsystem.dto.location.LocationUpdateReqDto;
import com.lawencon.assetsystem.service.LocationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("locations")
@SecurityRequirement(name = "bearerAuth")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertLocation(@RequestBody LocationInsertReqDto location) {
		final InsertResDto result = locationService.insert(location);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<LocationResDto>> getAll() {
		final List<LocationResDto> locations = locationService.getAll();
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<LocationResDto> getById(@RequestParam("code") long id) {
		final LocationResDto location = locationService.getById(id);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateLocation(@RequestBody LocationUpdateReqDto data) {
		final UpdateResDto location = locationService.updateLocation(data);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}
}
