package com.lawencon.assetsystem.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserProfileDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.UserProfile;

@Repository
@Profile("hql-query")
public class UserProfileDaoHQLImpl implements UserProfileDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserProfile getByID(Long id)  {
		final String sql = "SELECT "
				+ "	up.id AS profile_id, f.file_id"
				+ "FROM "
				+ "	UserProfile up "
				+ "INNER JOIN"
				+ "	File f ON up.file_id = f.id"
				+ "WHERE "
				+ "	up.id = :id";
		
		final Object profileObj = this.em.createQuery(sql)
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
		final int result = em.createQuery("DELETE FROM UserProfile up WHERE up.id = :id")
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
