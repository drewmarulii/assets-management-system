package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetStatusDao;
import com.lawencon.assetsystem.model.AssetStatus;

@Repository
@Profile("hql-query")
public class AssetStatusDaoHQLImpl implements AssetStatusDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AssetStatus> getAll() {
		final String sql = "SELECT "
				+ "	ast "
				+ "FROM "
				+ "	AssetStatus ast";
		final List<AssetStatus> assetStatus = this.em.createQuery(sql, AssetStatus.class)
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
