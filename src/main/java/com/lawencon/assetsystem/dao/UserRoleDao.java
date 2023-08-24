package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.UserRole;

public interface UserRoleDao {
	List<UserRole> getAll(); 
	UserRole getRoleById(Long roleId); 
	UserRole insert(UserRole role);
}
