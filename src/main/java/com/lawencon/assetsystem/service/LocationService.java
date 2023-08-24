package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.location.LocationInsertReqDto;
import com.lawencon.assetsystem.dto.location.LocationResDto;
import com.lawencon.assetsystem.dto.location.LocationUpdateReqDto;

public interface LocationService {
	List<LocationResDto> getAll(); 
	InsertResDto insert(LocationInsertReqDto location);
	LocationResDto getById(Long id);
	UpdateResDto updateLocation(LocationUpdateReqDto location);
}
