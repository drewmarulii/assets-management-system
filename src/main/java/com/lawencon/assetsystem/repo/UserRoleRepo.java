package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long>{

}
