package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserRoleDao;
import com.lawencon.assetsystem.model.UserRole;
import com.lawencon.assetsystem.repo.UserRoleRepo;

@Repository
@Profile("springdatajpa-query")
public class UserRoleDaoSpringDataJPAImpl implements UserRoleDao {

	private final UserRoleRepo userRoleRepo;
	
	public UserRoleDaoSpringDataJPAImpl (UserRoleRepo userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}
	
	@Override
	public List<UserRole> getAll() {
		final List<UserRole> roles = userRoleRepo.findAll();
		return roles;
	}

	@Override
	public UserRole getRoleById(Long roleId) {
		final UserRole role = userRoleRepo.findById(roleId).get();
		return role;
	}

	@Override
	public UserRole insert(UserRole role) {
		userRoleRepo.save(role);
		return role;
	}

}
