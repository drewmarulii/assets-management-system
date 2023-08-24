package com.lawencon.assetsystem.dao.impl.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.repo.FileRepo;

@Repository
@Profile("springdatajpa-query")
public class FileDaoSpringDataJPAImpl implements FileDao {

	private final FileRepo fileRepo;
	
	public FileDaoSpringDataJPAImpl(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
	}

	@Override
	public File getByID(Long id)  {
		final File file = fileRepo.findById(id).get();
		return file;
	}

	@Override
	public File insert(File file)  {
		fileRepo.save(file);
		return file;
	}

	@Override
	public boolean deleteByID(Long id)  {
		final int result = fileRepo.removeById(id);
		return result>0;
	}

}
