package com.lawencon.assetsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	List<User> getByRoleRoleCode(String roleCode);
	User getByUserEmail(String email);
}
