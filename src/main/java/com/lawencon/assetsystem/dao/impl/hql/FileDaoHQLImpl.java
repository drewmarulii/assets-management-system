package com.lawencon.assetsystem.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.model.File;

@Repository
@Profile("hql-query")
public class FileDaoHQLImpl implements FileDao {

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
		final int result = em.createQuery("DELETE FROM File f WHERE f.id = :id")
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

}
