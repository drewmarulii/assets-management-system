package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.UserRoleDao;
import com.lawencon.assetsystem.model.UserRole;

@Repository
@Profile("hql-query")
public class UserRoleDaoHQLImpl implements UserRoleDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<UserRole> getAll() {
		final String sql = "SELECT "
				+ "	ur "
				+ "FROM "
				+ "	UserRole ur"; 
		final List<UserRole> roles = this.em.createQuery(sql, UserRole.class)
				.getResultList();
		return roles;
	}

	@Override
	public UserRole getRoleById(Long roleId) {
		final UserRole role = this.em.find(UserRole.class, roleId);
		return role;
	}

	@Override
	public UserRole insert(UserRole role) {
		em.persist(role);
		return role;
	}

}
