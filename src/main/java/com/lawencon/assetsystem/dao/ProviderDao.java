package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Provider;

public interface ProviderDao {
	List<Provider> getAll();
	Provider insert(Provider provider);
	Provider getProviderById(Long id);
}
