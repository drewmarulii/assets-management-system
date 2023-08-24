package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.ProviderDao;
import com.lawencon.assetsystem.model.Provider;

@Repository
@Profile("hql-query")
public class ProviderDaoHQLImpl implements ProviderDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Provider> getAll() {
		final String sql = "SELECT " 
				+ "	p " 
				+ "FROM " 
				+ "	Provider p";
		final List<Provider> providers = this.em.createQuery(sql, Provider.class)
				.getResultList();

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
