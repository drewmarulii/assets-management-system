package com.lawencon.assetsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.Asset;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Long>{

	@Query(value = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	assets a "
				+ "INNER JOIN "
				+ "	asset_type aty ON a.type_id = aty.id "
				+ "INNER JOIN "
				+ "	company c ON c.id = a.company_id "
				+ "WHERE "
				+ "	type_code = :typeCode", nativeQuery = true)
	List<Asset> getAssetGeneral(@Param(value = "typeCode") String typeCode);

	@Query(value = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	assets "
				+ "WHERE "
				+ "	status_id = :id", nativeQuery = true)
	List<Asset> getByAssetStatusId(@Param(value = "id") Long id);
}
