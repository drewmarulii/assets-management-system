package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Supplier;

public interface SupplierDao {
	List<Supplier> getAll();
	Supplier getSupplierById(Long supplierid);
	Supplier insert(Supplier supplier);
}
