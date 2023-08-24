package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.ProviderDao;
import com.lawencon.assetsystem.model.Provider;
import com.lawencon.assetsystem.repo.ProviderRepo;

@Repository
@Profile("springdatajpa-query")
public class ProviderDaoSpringDataJPAImpl implements ProviderDao {

	private final ProviderRepo providerRepo;

	public ProviderDaoSpringDataJPAImpl(ProviderRepo providerRepo) {
		this.providerRepo = providerRepo;
	}

	@Override
	public List<Provider> getAll() {
		final List<Provider> providers = providerRepo.findAll();
		return providers;
	}

	@Override
	public Provider insert(Provider provider) {
		providerRepo.save(provider);
		return provider;
	}

	@Override
	public Provider getProviderById(Long id) {
		final Provider provider = providerRepo.findById(id).get();
		return provider;
	}

}
