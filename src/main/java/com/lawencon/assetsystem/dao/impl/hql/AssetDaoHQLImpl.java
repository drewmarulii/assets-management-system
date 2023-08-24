package com.lawencon.assetsystem.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.AssetDao;
import com.lawencon.assetsystem.model.Asset;
import com.lawencon.assetsystem.model.AssetStatus;
import com.lawencon.assetsystem.model.AssetType;
import com.lawencon.assetsystem.model.Company;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.InvoiceDetail;

@Repository
@Profile("hql-query")
public class AssetDaoHQLImpl implements AssetDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Asset> getAssetsByType(String...typeCodes) {
		final List<Asset> assets = new ArrayList<>();
		
		final String sql = "SELECT DISTINCT ON (a.asset_code) "
				+ "	a.id AS asset_id, "
				+ "	a.asset_code, "
				+ "	a.asset_name, "
				+ "	aty.type_name, "
				+ "	a.type_id, "
				+ "	a.status_id, "
				+ "	a.company_id, "
				+ "	a.file_id, "
				+ "	a.inv_detail_id "
				+ "FROM "
				+ "	Asset a "
				+ "LEFT JOIN "
				+ "	CheckoutDetails cd ON cd.asset_id = a.id "
				+ "LEFT JOIN "
				+ "	AssetType aty ON a.type_id = aty.id "
				+ "WHERE "
				+ "	a.asset_code NOT IN ("
				+ "		SELECT "
				+ "			a.asset_code "
				+ "		FROM "
				+ "			Asset a "
				+ "		INNER JOIN "
				+ "			CheckoutDetails cd "
				+ "		ON "
				+ "			cd.asset_id = a.id "
				+ "		WHERE "
				+ "			cd.checkin_date IS NULL AND cd.checkout_date IS NOT NULL) "
				+ "	AND "
				+ "		(aty.type_code = :typeCode OR aty.type_code = :typeCodee) "
				+ "	AND "
				+ "		a.status_id = 1";
		final List<?> assetObjs = this.em.createQuery(sql)
				.setParameter("typeCode", typeCodes[0])
				.setParameter("typeCodee", typeCodes[1])
				.getResultList();
		
		if (assetObjs.size() > 0) {
			for (Object assetObj : assetObjs) {
				final Object[] assetArr = (Object[]) assetObj;
				final Asset asset = new Asset();
				asset.setId(Long.valueOf(assetArr[0].toString()));
				asset.setAssetCode(assetArr[1].toString());
				asset.setAssetName(assetArr[2].toString());
				
				final AssetType type = new AssetType();
				type.setTypeName(assetArr[3].toString());
				type.setId(Long.valueOf(assetArr[4].toString()));
				asset.setAssetType(type);
				
				final AssetStatus status = new AssetStatus();
				status.setId(Long.valueOf(assetArr[5].toString()));
				asset.setAssetStatus(status);
				
				final Company company = new Company();
				company.setId(Long.valueOf(assetArr[6].toString()));
				asset.setCompany(company);
				
				final File file = new File();
				file.setId(Long.valueOf(assetArr[7].toString()));
				asset.setFile(file);
				
				final InvoiceDetail detail = new InvoiceDetail();
				detail.setId(Long.valueOf(assetArr[8].toString()));
				asset.setInvoiceDetail(detail);
				
				assets.add(asset);	
			}
		}
		
		return assets;
	}

	@Override
	public List<Asset> getAssetsGeneral(String type) {
		final List<Asset> assets = new ArrayList<>();
		final String sql = "SELECT "
				+ "	a.id AS asset_id, "
				+ "	a.asset_code, "
				+ "	a.asset_name, "
				+ "	aty.type_name, "
				+ "	a.type_id, "
				+ "	c.company_name "
				+ "FROM "
				+ "	Asset a "
				+ "INNER JOIN "
				+ "	AssetType aty ON a.type_id = aty.id "
				+ "INNER JOIN "
				+ "	Company c ON c.id = a.company_id "
				+ "WHERE "
				+ "	aty.type_code = :typeCode";
		final List<?> assetObjs = this.em.createQuery(sql)
				.setParameter("typeCode", type)
				.getResultList();
		
		if(assetObjs.size() > 0) {
			for (Object assetObj : assetObjs) {
				final Object[] assetArr = (Object[]) assetObj;
				final Asset asset = new Asset();
				asset.setId(Long.valueOf(assetArr[0].toString()));
				asset.setAssetCode(assetArr[1].toString());
				asset.setAssetName(assetArr[2].toString());
				
				final AssetType assetype = new AssetType();
				assetype.setTypeName(assetArr[3].toString());
				assetype.setId(Long.valueOf(assetArr[4].toString()));
				asset.setAssetType(assetype);
				
				final Company company = new Company();
				company.setCompanyName(assetArr[5].toString());
				asset.setCompany(company);

				assets.add(asset);
			}
		}

		return assets;
	}

	public List<Asset> getAssetByStatus(Long statusID) {
		final String sql ="SELECT "
				+ "	a "
				+ "FROM "
				+ "	Asset a "
				+ "WHERE "
				+ "	a.status_id = :id";
		final List<Asset> assets = this.em.createQuery(sql, Asset.class)
				.setParameter("id", statusID)
				.getResultList();
		
		return assets;
	}

	@Override
	public Asset insert(Asset assets) {
		em.persist(assets);
		return assets;
	}

	@Override
	public Asset getAssetById(Long assetid) {
		final Asset asset = this.em.find(Asset.class, assetid);
		return asset;
	}

	@Override
	public List<Asset> getAll() {
		final String sql = "SELECT "
				+ "	a "
				+ "FROM "
				+ "	Asset a";
		final List<Asset> asset = this.em.createQuery(sql, Asset.class)
				.getResultList();
		
		return asset;
	}

}
