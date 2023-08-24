package com.lawencon.assetsystem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.SupplierDao;
import com.lawencon.assetsystem.model.Supplier;

@Repository
@Profile("native-query")
public class SupplierDaoImpl implements SupplierDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	supplier";
		final List<Supplier> suppliers = this.em.createNativeQuery(sql, Supplier.class).getResultList();
		
		return suppliers;
	}

	@Override
	public Supplier getSupplierById(Long supplierid) {
		final Supplier supplier = this.em.find(Supplier.class, supplierid);
		
		return supplier;
	}

	@Override
	public Supplier insert(Supplier supplier) {
		em.persist(supplier);
		return supplier;
	}

}
