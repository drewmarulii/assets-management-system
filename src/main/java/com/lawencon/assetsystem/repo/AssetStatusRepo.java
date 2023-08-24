package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.AssetStatus;

@Repository
public interface AssetStatusRepo extends JpaRepository<AssetStatus, Long>{
	
}
