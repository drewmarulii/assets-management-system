package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.SupplierDao;
import com.lawencon.assetsystem.model.Supplier;
import com.lawencon.assetsystem.repo.SupplierRepo;

@Repository
@Profile("springdatajpa-query")
public class SupplierDaoSpringDataJPAImpl implements SupplierDao {
	
	private final SupplierRepo supplierRepo;

	public SupplierDaoSpringDataJPAImpl(SupplierRepo supplierRepo) {
		this.supplierRepo = supplierRepo;
	}

	@Override
	public List<Supplier> getAll() {
		final List<Supplier> suppliers = supplierRepo.findAll();
		return suppliers;
	}

	@Override
	public Supplier getSupplierById(Long supplierid) {
		final Supplier supplier = supplierRepo.findById(supplierid).get();
		return supplier;
	}

	@Override
	public Supplier insert(Supplier supplier) {
		supplierRepo.save(supplier);
		return supplier;
	}

}
