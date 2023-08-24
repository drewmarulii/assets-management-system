package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long>{

}
