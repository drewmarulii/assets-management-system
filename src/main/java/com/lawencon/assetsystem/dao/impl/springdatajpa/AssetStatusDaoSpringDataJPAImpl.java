package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetStatusDao;
import com.lawencon.assetsystem.model.AssetStatus;
import com.lawencon.assetsystem.repo.AssetStatusRepo;

@Repository
@Profile("springdatajpa-query")
public class AssetStatusDaoSpringDataJPAImpl implements AssetStatusDao {

	private final AssetStatusRepo assetStatusRepo;
	
	public AssetStatusDaoSpringDataJPAImpl(AssetStatusRepo assetStatusRepo) {
		this.assetStatusRepo = assetStatusRepo;
	}

	@Override
	public List<AssetStatus> getAll() {
		final List<AssetStatus> assetStatus = assetStatusRepo.findAll();
		
		return assetStatus;
	}

	@Override
	public AssetStatus getAssetStatusById(Long id) {
		final AssetStatus assetStatus = assetStatusRepo.findById(id).get();
		return assetStatus;
	}

	@Override
	public AssetStatus insert(AssetStatus assetStatus) {
		assetStatusRepo.save(assetStatus);
		return assetStatus;
	}

}
