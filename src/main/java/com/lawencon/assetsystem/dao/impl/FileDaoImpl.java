package com.lawencon.assetsystem.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.model.File;

@Repository
@Profile("native-query")
public class FileDaoImpl implements FileDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public File getByID(Long id)  {
		final File file = this.em.find(File.class, id);
		return file;
	}

	@Override
	public File insert(File file)  {
		em.persist(file);
		return file;
	}

	@Override
	public boolean deleteByID(Long id)  {
		final int result = em.createNativeQuery("DELETE FROM file WHERE id = :id")
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

}
