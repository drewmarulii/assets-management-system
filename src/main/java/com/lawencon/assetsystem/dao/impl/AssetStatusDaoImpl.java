package com.lawencon.assetsystem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetStatusDao;
import com.lawencon.assetsystem.model.AssetStatus;

@Repository
@Profile("native-query")
public class AssetStatusDaoImpl implements AssetStatusDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetStatus> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	asset_status";
		final List<AssetStatus> assetStatus = this.em.createNativeQuery(sql, AssetStatus.class)
				.getResultList();
		
		return assetStatus;
	}

	@Override
	public AssetStatus getAssetStatusById(Long id) {
		final AssetStatus assetStatus = this.em.find(AssetStatus.class, id);
		return assetStatus;
	}

	@Override
	public AssetStatus insert(AssetStatus assetStatus) {
		em.persist(assetStatus);
		return assetStatus;
	}

}
