package com.lawencon.assetsystem.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserDao;
import com.lawencon.assetsystem.model.User;
import com.lawencon.assetsystem.model.UserProfile;
import com.lawencon.assetsystem.model.UserRole;

@Repository
@Profile("hql-query")
public class UserDaoHQLImpl implements UserDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> getAll() {
		final String sql = "SELECT "
				+ "	u "
				+ "FROM "
				+ "	User u";
		final List<User> users = this.em.createQuery(sql, User.class)
				.getResultList();
		
		return users;
	}

	@Override
	public List<User> getByRoleCode(String roleCode) {
		final List<User> users = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	u.id as user_id, "
				+ " u.role.roleCode,"
				+ " u.role.roleName,"
				+ " u.profile.userFullname "
				+ "FROM "
				+ "	User u "
				+ "WHERE "
				+ "	u.role.roleCode = :roleCode";
		
		final List<?> userObjs = this.em.createQuery(sql)
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
				+ "	u.profile.userFullname, "
				+ "	u.role.roleCode, "
				+ "	u.userPassword "
				+ "FROM "
				+ "	User u "
				+ "WHERE "
				+ "	u.userEmail = :userEmail";
		
		final Object userObj = this.em.createQuery(sql)
				.setParameter("userEmail", email)
				.getSingleResult();
		
		final Object[] userArr = (Object[]) userObj;
		
		User user = null;

		if(userArr.length > 0) {
			user = new User();
			user.setId(Long.valueOf(userArr[0].toString()));
						
			final UserProfile profile = new UserProfile();
			profile.setUserFullname(userArr[1].toString());
			user.setProfile(profile);
			
			final UserRole role = new UserRole();
			role.setRoleCode(userArr[2].toString());
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
