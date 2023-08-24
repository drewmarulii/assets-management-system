package com.lawencon.assetsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.User;
import com.lawencon.assetsystem.model.UserProfile;
import com.lawencon.assetsystem.model.UserRole;

@Repository
@Profile("native-query")
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	user_account "
				+ "ORDER BY"
				+ "	id";
		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();
		
		return users;
	}

	@Override
	public List<User> getByRoleCode(String roleCode) {
		final List<User> users = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	u.id as user_id, role_code, role_name, user_fullname "
				+ "FROM "
				+ "	user_account u "
				+ "INNER JOIN "
				+ "	user_role r ON r.id = u.role_id "
				+ "INNER JOIN "
				+ "	user_profile p ON p.id = u.profile_id "
				+ "WHERE "
				+ "	r.role_code = :roleCode";
		
		final List<?> userObjs = this.em.createNativeQuery(sql)
				.setParameter("roleCode", roleCode)
				.getResultList();
		
		if(userObjs.size() > 0) {
			for(Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				final User user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
							
				final UserRole role = new UserRole();
				role.setRoleCode(userArr[1].toString());
				role.setRoleName(userArr[2].toString());
				user.setRole(role);
				
				final UserProfile profile = new UserProfile();
				profile.setUserFullname(userArr[3].toString());
				user.setProfile(profile);
				users.add(user);
			}
		}
		
		return users;
	}

	@Override
	public User insert(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User getByUsername(String email) {
		final String sql = "SELECT "
				+ "	u.id AS user_id, "
				+ "	user_fullname, "
				+ "	role_name, "
				+ "	user_password, "
				+ "	file_id, "
				+ "	role_code "
				+ "FROM "
				+ "	user_account u "
				+ "INNER JOIN "
				+ "	user_role r ON r.id = u.role_id "
				+ "INNER JOIN "
				+ "	user_profile p ON p.id = u.profile_id "
				+ "WHERE "
				+ "	u.user_email = :userEmail";
		
		final Object userObj = this.em.createNativeQuery(sql)
				.setParameter("userEmail", email)
				.getSingleResult();
		
		final Object[] userArr = (Object[]) userObj;
		
		User user = null;

		if(userArr.length > 0) {
			user = new User();
			user.setId(Long.valueOf(userArr[0].toString()));
						
			final UserProfile profile = new UserProfile();
			profile.setUserFullname(userArr[1].toString());
			final File file = new File();
			file.setId(Long.valueOf(userArr[4].toString()));
			profile.setFile(file);
			user.setProfile(profile);
			
			final UserRole role = new UserRole();
			role.setRoleCode(userArr[5].toString());
			role.setRoleName(userArr[2].toString());
			user.setRole(role);
			
			user.setUserPassword(userArr[3].toString());
		}
			
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		final User user = this.em.find(User.class, userId);
		return user;
	}

	
}
