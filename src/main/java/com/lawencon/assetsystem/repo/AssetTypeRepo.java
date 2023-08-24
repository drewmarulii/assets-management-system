package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.AssetType;

@Repository
public interface AssetTypeRepo extends JpaRepository<AssetType, Long>{
	
}
