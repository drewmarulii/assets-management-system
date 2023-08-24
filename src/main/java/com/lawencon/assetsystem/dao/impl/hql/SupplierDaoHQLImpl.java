package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.SupplierDao;
import com.lawencon.assetsystem.model.Supplier;

@Repository
@Profile("hql-query")
public class SupplierDaoHQLImpl implements SupplierDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Supplier> getAll() {
		final String sql = "SELECT "
				+ "	s "
				+ "FROM "
				+ "	Supplier s";
		final List<Supplier> suppliers = this.em.createQuery(sql, Supplier.class)
				.getResultList();
		
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
