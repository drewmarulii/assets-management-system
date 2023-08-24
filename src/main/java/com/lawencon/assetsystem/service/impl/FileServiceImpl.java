package com.lawencon.assetsystem.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	private final FileDao fileDao;
	
	@PersistenceContext
	private EntityManager em;
	
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public File getById(Long id) {
		final File file = fileDao.getByID(id);
		
		return file;
	}
	
	@Override
	public File insert(File file) {
		final File newfile = fileDao.insert(file);
		
		return newfile;
	}

	@Override
	public boolean delete(Long id) {
		final boolean file = fileDao.deleteByID(id);
	
		return file;
	}
	
}
