package com.lawencon.assetsystem.dao.impl.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserProfileDao;
import com.lawencon.assetsystem.model.UserProfile;
import com.lawencon.assetsystem.repo.UserProfileRepo;

@Repository
@Profile("springdatajpa-query")
public class UserProfileDaoSpringDataJPAImpl implements UserProfileDao {

	private final UserProfileRepo userProfileRepo;

	public UserProfileDaoSpringDataJPAImpl(UserProfileRepo userProfileRepo) {
		this.userProfileRepo = userProfileRepo;
	}

	@Override
	public UserProfile getByID(Long id)  {
		final UserProfile profile = userProfileRepo.findById(id).get();
		return profile;
	}
	
	@Override
	public UserProfile insert(UserProfile userProfile)  {
		userProfileRepo.save(userProfile);
		return userProfile;
	}
	
	@Override
	public boolean deleteByID(Long id)  {	
		final int result = userProfileRepo.removeById(id);
		return result>0;
	}

	@Override
	public UserProfile getProfileById(Long profileid) {
		final UserProfile profile = userProfileRepo.findById(profileid).get();
		return profile;
	}

}
