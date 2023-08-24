package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetDao;
import com.lawencon.assetsystem.model.Asset;
import com.lawencon.assetsystem.repo.AssetRepo;

@Repository
@Profile("springdatajpa-query")
public class AssetDaoSpringDataJPAImpl implements AssetDao {

	private final AssetRepo assetRepo;

	public AssetDaoSpringDataJPAImpl(AssetRepo assetRepo) {
		this.assetRepo = assetRepo;
	}

	@Override
	public List<Asset> getAssetsByType(String...typeCodes) {
		final List<Asset> assets = new ArrayList<>();
		return assets;
	}

	@Override
	public List<Asset> getAssetsGeneral(String type) {
		final List<Asset> assets = assetRepo.getAssetGeneral(type);
		return assets;
	}

	public List<Asset> getAssetByStatus(Long statusId) {
		final List<Asset> assets = assetRepo.getByAssetStatusId(statusId);		
		return assets;
	}

	@Override
	public Asset insert(Asset assets) {
		assetRepo.save(assets);
		return assets;
	}

	@Override
	public Asset getAssetById(Long assetid) {
		final Asset asset = assetRepo.findById(assetid).get();
		return asset;
	}

	@Override
	public List<Asset> getAll() {
		final List<Asset> asset = assetRepo.findAll();
		return asset;
	}

}
