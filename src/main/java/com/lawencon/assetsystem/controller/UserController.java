package com.lawencon.assetsystem.controller;

import java.util.List;

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
import com.lawencon.assetsystem.dto.user.UserInsertReqDto;
import com.lawencon.assetsystem.dto.user.UserListResDto;
import com.lawencon.assetsystem.dto.user.UserPasswordChangeReqDto;
import com.lawencon.assetsystem.dto.user.UserProfileUpdateReqDto;
import com.lawencon.assetsystem.dto.user.UserResDto;
import com.lawencon.assetsystem.dto.user.UserUpdateIsActiveReqDto;
import com.lawencon.assetsystem.dto.userrole.UserRoleResDto;
import com.lawencon.assetsystem.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserListResDto>> getAll() {
		final List<UserListResDto> users = userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<UserListResDto> getById(@RequestParam("code") long code) {
		final UserListResDto profile = userService.getByID(code);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserResDto>> getByRoleCode(@RequestParam("code") String code) {
		final List<UserResDto> users = userService.getByRoleCode(code);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertUser(@RequestBody UserInsertReqDto data) {
		final InsertResDto response = userService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<UserRoleResDto>> getAllRole() {
		final List<UserRoleResDto> roles = userService.getAllRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateIsActive(@RequestBody UserUpdateIsActiveReqDto isActive){
		final UpdateResDto result = userService.isActive(isActive);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping("/change-password")
	public ResponseEntity<UpdateResDto> changePassword(@RequestBody UserPasswordChangeReqDto data) {
		final UpdateResDto result = userService.changePassword(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping("/profile")
	public ResponseEntity<UpdateResDto> updateProfile(@RequestBody UserProfileUpdateReqDto data) {
		final UpdateResDto result = userService.updateProfile(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
