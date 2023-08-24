package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.asset.AssetGeneralResDto;
import com.lawencon.assetsystem.dto.asset.AssetInsertReqDto;
import com.lawencon.assetsystem.dto.asset.AssetResDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateReqDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateStatusReqDto;
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusInsertReqDto;
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusResDto;
import com.lawencon.assetsystem.dto.assettype.AssetTypeInsertReqDto;
import com.lawencon.assetsystem.dto.assettype.AssetTypeResDto;
import com.lawencon.assetsystem.model.Asset;

public interface AssetService {
	List<AssetResDto> getAll();
	List<AssetResDto> getItemByType(int option); 
	List<AssetGeneralResDto> getAssetGeneral(); 
	List<Asset> getAssetByStatus();
	List<AssetStatusResDto> getAllStatus();
	List<AssetTypeResDto> getAllType();
	InsertResDto insert(AssetInsertReqDto asset);
	UpdateResDto update(AssetUpdateReqDto asset);
	UpdateResDto updateStatus(AssetUpdateStatusReqDto asset);
	InsertResDto insertAssetStatus(AssetStatusInsertReqDto status);
	InsertResDto insertAssetType(AssetTypeInsertReqDto type);
	AssetResDto getByID(Long id);
}
