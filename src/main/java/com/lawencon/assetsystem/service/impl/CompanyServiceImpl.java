package com.lawencon.assetsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.company.CompanyInsertReqDto;
import com.lawencon.assetsystem.dto.company.CompanyResDto;
import com.lawencon.assetsystem.dto.company.CompanyUpdateReqDto;
import com.lawencon.assetsystem.model.Company;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.service.CompanyService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyDao companyDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public CompanyServiceImpl(CompanyDao companyDao, FileDao fileDao, PrincipalService principalService) {
		this.companyDao = companyDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}
	
	@Override
	public List<CompanyResDto> getAll() {
		final List<CompanyResDto> companies = new ArrayList<>();
		
		companyDao.getAll().forEach(c -> {
			final CompanyResDto company = new CompanyResDto();
			company.setId(c.getId());
			company.setFileId(c.getFile().getId());
			company.setFile(c.getFile().getFile());
			company.setFileExtension(c.getFile().getFileExtension());
			company.setCompanyName(c.getCompanyName());
			company.setCompanyAddress(c.getCompanyAddress());
			company.setCompanyPhone(c.getCompanyPhone());
			companies.add(company);
		});
		
		return companies;
	}

	@Transactional
	@Override
	public InsertResDto insert(CompanyInsertReqDto company) {
		InsertResDto result = new InsertResDto();
		
		final Company newCompany = new Company();
		newCompany.setCompanyName(company.getCompanyName());
		newCompany.setCompanyAddress(company.getCompanyAddress());
		newCompany.setCompanyPhone(company.getCompanyPhone());
		
		final File file = new File();
		file.setFile(company.getFile());
		file.setFileExtension(company.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		final File photo = fileDao.insert(file);
		
		newCompany.setFile(photo);
		newCompany.setCreatedBy(principalService.getPrincipal());
		companyDao.insert(newCompany);
		
		result.setId(newCompany.getId());
		result.setMessage("Insert Company Success!");
		
		return result;
	}

	@Transactional
	@Override
	public CompanyResDto getById(Long id) {
		final CompanyResDto company = new CompanyResDto();
		final Company com = companyDao.getCompanyById(id);
		company.setId(com.getId());
		company.setFileId(com.getFile().getId());
		company.setFile(com.getFile().getFile());
		company.setFileExtension(com.getFile().getFileExtension());
		company.setCompanyName(com.getCompanyName());
		company.setCompanyAddress(com.getCompanyAddress());
		company.setCompanyPhone(com.getCompanyPhone());
		
		return company;
	}

	@Transactional	
	@Override
	public UpdateResDto updateCompany(CompanyUpdateReqDto data) {
		final Company company = companyDao.getCompanyById(data.getId());
		company.setCompanyName(data.getCompanyName());
		company.setCompanyAddress(data.getCompanyAddress());
		company.setCompanyPhone(data.getCompanyPhone());
		
		if (data.getFile() != null) {
			final long fileID = data.getFileId();
			final File file = new File();
			file.setFile(data.getFile());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy(principalService.getPrincipal());
			fileDao.insert(file);
			company.setFile(file);
			fileDao.deleteByID(fileID);
		}
		
		company.setUpdatedBy(principalService.getPrincipal());
		
		em.flush();
		
		final UpdateResDto result = new UpdateResDto();
		result.setVersion(company.getVersion());
		result.setMessage("Company Update");
		
		return result;
	}

}
