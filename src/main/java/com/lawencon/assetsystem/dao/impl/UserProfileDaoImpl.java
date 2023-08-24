package com.lawencon.assetsystem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserProfileDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.UserProfile;

@Repository
@Profile("native-query")
public class UserProfileDaoImpl implements UserProfileDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserProfile getByID(Long id)  {
		final String sql = "SELECT "
				+ "	up.id AS profile_id, file_id"
				+ "FROM "
				+ "	user_profile up "
				+ "INNER JOIN"
				+ "	file f ON up.file_id = f.id"
				+ "WHERE "
				+ "	up.id = :id";
		
		final Object profileObj = this.em.createNativeQuery(sql)
				.setParameter("id", id)
				.getSingleResult();
		
		final Object[] profileArr = (Object[]) profileObj;
		
		UserProfile profile = null;
		
		if (profileArr.length > 0) {
			profile = new UserProfile();
			profile.setId(Long.valueOf(profileArr[0].toString()));
			
			final File file = new File();
			file.setId(Long.valueOf(profileArr[1].toString()));
			profile.setFile(file);
		}
		
		return profile;
	}
	
	@Override
	public UserProfile insert(UserProfile userProfile)  {
		em.persist(userProfile);
		return userProfile;
	}
	
	@Override
	public boolean deleteByID(Long id)  {		
		final int result = em.createNativeQuery("DELETE FROM user_profile WHERE id = :id")
				.setParameter("id", id)
				.executeUpdate();

		return result>0;
	}

	@Override
	public UserProfile getProfileById(Long profileid) {
		final UserProfile profile = this.em.find(UserProfile.class, profileid);
		
		return profile;
	}

}
