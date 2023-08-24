package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.AssetStatus;

public interface AssetStatusDao {
	List<AssetStatus> getAll();
	AssetStatus getAssetStatusById(Long id);
	AssetStatus insert(AssetStatus assetStatus);
}
