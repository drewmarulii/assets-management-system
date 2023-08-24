package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.User;

public interface UserDao {
	List<User> getAll(); 
	List<User> getByRoleCode(String roleCode);
	User insert(User user);
	User getByUsername(String email);
	User getUserById(Long userId);
}
