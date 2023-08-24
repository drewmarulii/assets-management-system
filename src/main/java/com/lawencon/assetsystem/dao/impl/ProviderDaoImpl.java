package com.lawencon.assetsystem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.ProviderDao;
import com.lawencon.assetsystem.model.Provider;

@Repository
@Profile("native-query")
public class ProviderDaoImpl implements ProviderDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Provider> getAll() {
		final String sql = "SELECT " 
				+ "	* " 
				+ "FROM " 
				+ "	provider";
		final List<Provider> providers = this.em.createNativeQuery(sql, Provider.class).getResultList();

		return providers;

	}

	@Override
	public Provider insert(Provider provider) {
		em.persist(provider);
		return provider;
	}

	@Override
	public Provider getProviderById(Long id) {
		final Provider provider = this.em.find(Provider.class, id);
		return provider;
	}

}
