package com.lawencon.assetsystem.dao;

import com.lawencon.assetsystem.model.UserProfile;

public interface UserProfileDao {
	UserProfile getByID(Long id);
	UserProfile insert(UserProfile userProfile);
	boolean deleteByID(Long id);
	UserProfile getProfileById(Long profileid);
}
