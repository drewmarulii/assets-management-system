package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.provider.ProviderInsertReqDto;
import com.lawencon.assetsystem.dto.provider.ProviderResDto;

public interface ProviderService {
	List<ProviderResDto> getAll();
	InsertResDto insert(ProviderInsertReqDto provider);
}
