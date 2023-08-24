package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.AssetType;

public interface AssetTypeDao {
	List<AssetType> getAll();
	AssetType getAssetTypeById(Long id);
	AssetType insert(AssetType assetType);
}
