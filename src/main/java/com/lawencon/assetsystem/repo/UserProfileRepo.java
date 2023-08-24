package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.UserProfile;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long>{

	Integer removeById(Long id);
}
