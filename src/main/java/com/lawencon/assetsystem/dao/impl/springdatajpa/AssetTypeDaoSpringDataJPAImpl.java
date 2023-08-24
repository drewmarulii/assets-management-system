package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetTypeDao;
import com.lawencon.assetsystem.model.AssetType;
import com.lawencon.assetsystem.repo.AssetTypeRepo;

@Repository
@Profile("springdatajpa-query")
public class AssetTypeDaoSpringDataJPAImpl implements AssetTypeDao {
	
	private final AssetTypeRepo assetTypeRepo;

	public AssetTypeDaoSpringDataJPAImpl(AssetTypeRepo assetTypeRepo) {
		this.assetTypeRepo = assetTypeRepo;
	}

	@Override
	public List<AssetType> getAll() {
		final List<AssetType> assetType = assetTypeRepo.findAll();
		return assetType;
	}

	@Override
	public AssetType getAssetTypeById(Long id) {
		final AssetType assetType = assetTypeRepo.findById(id).get();
		return assetType;
	}

	@Override
	public AssetType insert(AssetType assetType) {
		assetTypeRepo.save(assetType);
		return assetType;
	}

	
}
