package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserDao;
import com.lawencon.assetsystem.model.User;
import com.lawencon.assetsystem.repo.UserRepo;

@Repository
@Profile("springdatajpa-query")
public class UserDaoSpringDataJPAImpl implements UserDao{
	
	private final UserRepo userRepo;
	
	public UserDaoSpringDataJPAImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAll() {
		final List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public List<User> getByRoleCode(String roleCode) {
		final List<User> users = userRepo.getByRoleRoleCode(roleCode);
		return users;
	}

	@Override
	public User insert(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		final User user = userRepo.findById(userId).get();
		return user;
	}

	@Override
	public User getByUsername(String email) {
		final User user = userRepo.getByUserEmail(email);
		return user;
	}
}
