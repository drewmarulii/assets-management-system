package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.company.CompanyInsertReqDto;
import com.lawencon.assetsystem.dto.company.CompanyResDto;
import com.lawencon.assetsystem.dto.company.CompanyUpdateReqDto;

public interface CompanyService {
	List<CompanyResDto> getAll();
	InsertResDto insert(CompanyInsertReqDto company);
	CompanyResDto getById(Long id);
	UpdateResDto updateCompany(CompanyUpdateReqDto company);
}
