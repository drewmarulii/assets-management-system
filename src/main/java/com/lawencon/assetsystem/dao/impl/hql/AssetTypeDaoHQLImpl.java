package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetTypeDao;
import com.lawencon.assetsystem.model.AssetType;

@Repository
@Profile("hql-query")
public class AssetTypeDaoHQLImpl implements AssetTypeDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AssetType> getAll() {
		final String sql = "SELECT "
				+ "	aty "
				+ "FROM "
				+ "	AssetType aty";
		final List<AssetType> assetType = this.em.createQuery(sql, AssetType.class)
				.getResultList();
		
		return assetType;
	}

	@Override
	public AssetType getAssetTypeById(Long id) {
		final AssetType assetType = this.em.find(AssetType.class, id);
		return assetType;
	}

	@Override
	public AssetType insert(AssetType assetType) {
		em.persist(assetType);
		return assetType;
	}

	
}
