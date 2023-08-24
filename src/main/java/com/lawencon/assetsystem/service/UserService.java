package com.lawencon.assetsystem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.login.LoginReqDto;
import com.lawencon.assetsystem.dto.login.LoginResDto;
import com.lawencon.assetsystem.dto.user.UserInsertReqDto;
import com.lawencon.assetsystem.dto.user.UserListResDto;
import com.lawencon.assetsystem.dto.user.UserPasswordChangeReqDto;
import com.lawencon.assetsystem.dto.user.UserProfileUpdateReqDto;
import com.lawencon.assetsystem.dto.user.UserResDto;
import com.lawencon.assetsystem.dto.user.UserUpdateIsActiveReqDto;
import com.lawencon.assetsystem.dto.userrole.UserRoleResDto;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.UserProfile;

public interface UserService extends UserDetailsService {
	List<UserResDto> getAll(); 
	List<UserListResDto> getAllUser();
	List<UserRoleResDto> getAllRoles();
	InsertResDto insert(UserInsertReqDto userInsertReqDto);
	UserProfile updatePicture(Long profileId, File file);
	LoginResDto login(LoginReqDto user);
	List<UserResDto> getByRoleCode(String roleCode);
	UpdateResDto isActive(UserUpdateIsActiveReqDto isActive);
	UserListResDto getByID(long id);
	UpdateResDto changePassword(UserPasswordChangeReqDto data);
	UpdateResDto updateProfile(UserProfileUpdateReqDto data);
}
