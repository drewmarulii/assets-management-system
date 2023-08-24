package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Asset;

public interface AssetDao {
	List<Asset> getAll();
	Asset insert(Asset asset);
	List<Asset> getAssetsByType(String...typeCodes);
	List<Asset> getAssetsGeneral(String type);
	List<Asset> getAssetByStatus(Long statusID);
	Asset getAssetById(Long assetid);
}
