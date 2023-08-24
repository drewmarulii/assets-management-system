package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dao.ProviderDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.provider.ProviderInsertReqDto;
import com.lawencon.assetsystem.dto.provider.ProviderResDto;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.Provider;
import com.lawencon.assetsystem.service.PrincipalService;
import com.lawencon.assetsystem.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	
	private final ProviderDao providerDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public ProviderServiceImpl(ProviderDao providerDao, FileDao fileDao, PrincipalService principalService) {
		this.providerDao = providerDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Override
	public List<ProviderResDto> getAll() {
		final List<ProviderResDto> providers = new ArrayList<>();
		
		providerDao.getAll().forEach(p -> {
			final ProviderResDto provider = new ProviderResDto();
			provider.setId(p.getId());
			provider.setFileId(p.getFile().getId());
			provider.setFile(p.getFile().getFile());
			provider.setFileExtension(p.getFile().getFileExtension());
			provider.setProviderCode(p.getProviderCode());
			provider.setProviderName(p.getProviderName());
			providers.add(provider);
		});
		
		return providers;
	}

	@Transactional
	@Override
	public InsertResDto insert(ProviderInsertReqDto provider) {
		InsertResDto result = new InsertResDto(); 
		
		final Provider newProvider = new Provider();
		final String randomCode = generateID(5);
		newProvider.setProviderCode(randomCode);
		newProvider.setProviderName(provider.getProviderName());
		
		File file = new File();
		file.setFile(provider.getFile());
		file.setFileExtension(provider.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		file = fileDao.insert(file);
		
		newProvider.setFile(file);
		newProvider.setCreatedBy(principalService.getPrincipal());
		providerDao.insert(newProvider);
		
		result.setId(newProvider.getId());
		result.setMessage("Insert Provider Success!");
		return result;
	}

}
