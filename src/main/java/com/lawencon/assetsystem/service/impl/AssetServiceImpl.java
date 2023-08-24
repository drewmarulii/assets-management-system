package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.constant.TypeAsset;
import com.lawencon.assetsystem.dao.AssetDao;
import com.lawencon.assetsystem.dao.AssetStatusDao;
import com.lawencon.assetsystem.dao.AssetTypeDao;
import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dao.InvoiceDetailDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.asset.AssetGeneralResDto;
import com.lawencon.assetsystem.dto.asset.AssetInsertReqDto;
import com.lawencon.assetsystem.dto.asset.AssetResDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateReqDto;
import com.lawencon.assetsystem.dto.asset.AssetUpdateStatusReqDto;
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusInsertReqDto;
import com.lawencon.assetsystem.dto.assetstatus.AssetStatusResDto;
import com.lawencon.assetsystem.dto.assettype.AssetTypeInsertReqDto;
import com.lawencon.assetsystem.dto.assettype.AssetTypeResDto;
import com.lawencon.assetsystem.model.Asset;
import com.lawencon.assetsystem.model.AssetStatus;
import com.lawencon.assetsystem.model.AssetType;
import com.lawencon.assetsystem.model.Company;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.InvoiceDetail;
import com.lawencon.assetsystem.service.AssetService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class AssetServiceImpl implements AssetService {

	private final AssetDao assetDao;
	private final AssetStatusDao assetStatusDao;
	private final AssetTypeDao assetTypeDao;
	private final FileDao fileDao;
	private final CompanyDao companyDao;
	private final InvoiceDetailDao invoiceDetailDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public AssetServiceImpl(AssetDao assetDao, AssetStatusDao assetStatusDao, AssetTypeDao assetTypeDao,
			FileDao fileDao, CompanyDao companyDao, InvoiceDetailDao invoiceDetailDao,
			PrincipalService principalService) {
		this.assetDao = assetDao;
		this.assetStatusDao = assetStatusDao;
		this.assetTypeDao = assetTypeDao;
		this.fileDao = fileDao;
		this.companyDao = companyDao;
		this.invoiceDetailDao = invoiceDetailDao;
		this.principalService = principalService;
	}

	@Override
	public List<AssetResDto> getAll() {
		final List<AssetResDto> assets = new ArrayList<>();

		assetDao.getAll().forEach(a -> {
			final AssetResDto asset = new AssetResDto();
			asset.setId(a.getId());
			asset.setFile(a.getFile().getId());
			asset.setAssetCode(a.getAssetCode());
			asset.setAssetName(a.getAssetName());
			asset.setTypeName(a.getAssetType().getTypeName());
			asset.setStatusName(a.getAssetStatus().getStatusName());
			asset.setCompanyName(a.getCompany().getCompanyName());

			assets.add(asset);
		});

		return assets;
	}

	@Override
	public List<AssetResDto> getItemByType(int option) {
		final List<AssetResDto> assets = new ArrayList<>();
		
		final AssetResDto asset = new AssetResDto();
		if(option == 1 || option == 2) {
			assetDao.getAssetsByType(TypeAsset.GENERAL.getTypeCode(), TypeAsset.CONSUMABLE.getTypeCode()).forEach(a -> {
				asset.setId(a.getId());
				asset.setFile(a.getFile().getId());
				asset.setAssetCode(a.getAssetCode());
				asset.setAssetName(a.getAssetName());
				asset.setTypeName(a.getAssetType().getTypeName());
				final AssetStatus status = assetStatusDao.getAssetStatusById(a.getAssetStatus().getId());
				asset.setStatusName(status.getStatusName());
				final Company company = companyDao.getCompanyById(a.getCompany().getId());
				asset.setCompanyName(company.getCompanyName());
				assets.add(asset);	
			});	
		} else if (option == 3) {
			assetDao.getAssetsByType(TypeAsset.LICENSES.getTypeCode(), TypeAsset.COMPONENTS.getTypeCode()).forEach(a -> {
				asset.setId(a.getId());
				asset.setFile(a.getFile().getId());
				asset.setAssetCode(a.getAssetCode());
				asset.setAssetName(a.getAssetName());
				asset.setTypeName(a.getAssetType().getTypeName());
				final AssetStatus status = assetStatusDao.getAssetStatusById(a.getAssetStatus().getId());
				asset.setStatusName(status.getStatusName());
				final Company company = companyDao.getCompanyById(a.getCompany().getId());
				asset.setCompanyName(company.getCompanyName());
				assets.add(asset);	
			});
		}
		
		return assets;
	}

	@Override
	public List<AssetGeneralResDto> getAssetGeneral() {

		final List<AssetGeneralResDto> assets = new ArrayList<>();

		assetDao.getAssetsGeneral("AT01").forEach(a -> {
			final AssetGeneralResDto asset = new AssetGeneralResDto();
			asset.setId(a.getId());
			asset.setAssetCode(a.getAssetCode());
			asset.setAssetName(a.getAssetName());

			assets.add(asset);
		});

		return assets;
	}

	public List<Asset> getAssetByStatus() {
		return assetDao.getAssetByStatus((long) 5);
	}

	@Transactional
	@Override
	public UpdateResDto updateStatus(AssetUpdateStatusReqDto asset) {
		final Asset uptAsset = assetDao.getAssetById(asset.getAssetId());
		final AssetStatus status = assetStatusDao.getAssetStatusById(asset.getStatusId());
		uptAsset.setAssetStatus(status);

		em.flush();
		final UpdateResDto result = new UpdateResDto();
		result.setVersion(uptAsset.getVersion());
		result.setMessage("Asset Status Has Been Updated Succesfully");
		return result;
	}

	@Transactional
	@Override
	public UpdateResDto update(AssetUpdateReqDto asset) {
		final Asset uptAsset = assetDao.getAssetById(asset.getAssetId());
		em.detach(uptAsset);
		uptAsset.setAssetName(asset.getAssetName());

		final AssetType type = assetTypeDao.getAssetTypeById(asset.getTypeId());
		uptAsset.setAssetType(type);

		final AssetStatus status = assetStatusDao.getAssetStatusById(asset.getStatusId());
		uptAsset.setAssetStatus(status);

		final Company company = companyDao.getCompanyById(asset.getCompanyId());
		uptAsset.setCompany(company);

		final InvoiceDetail detail = invoiceDetailDao.getInvDetailById(asset.getInvDetailId());
		uptAsset.setInvoiceDetail(detail);
		uptAsset.setVersion(asset.getVersion());
		uptAsset.setUpdatedBy(principalService.getPrincipal());

		File file = null;

		if (asset.getFile() != null) {
			file = fileDao.getByID(uptAsset.getFile().getId());
			file.setFile(asset.getFile());
			file.setFileExtension(asset.getFileExtension());
			file.setUpdatedBy(principalService.getPrincipal());
			fileDao.insert(file);
			uptAsset.setFile(file);
		} else {
			file = fileDao.getByID(uptAsset.getFile().getId());
			uptAsset.setFile(file);
		}

		em.merge(uptAsset);
		em.flush();
		final UpdateResDto result = new UpdateResDto();
		result.setVersion(uptAsset.getVersion());
		result.setMessage("Asset Has Been Updated Succesfully");
		return result;
	}

	@Override
	public List<AssetStatusResDto> getAllStatus() {

		final List<AssetStatusResDto> statuss = new ArrayList<>();

		assetStatusDao.getAll().forEach(s -> {
			final AssetStatusResDto status = new AssetStatusResDto();
			status.setId(s.getId());
			status.setStatusCode(s.getStatusCode());
			status.setStatusName(s.getStatusName());
			statuss.add(status);
		});

		return statuss;
	}

	@Transactional
	@Override
	public InsertResDto insert(AssetInsertReqDto asset) {

		InsertResDto result = new InsertResDto();

		File file = new File();
		file.setFile(asset.getFile());
		file.setFileExtension(asset.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		file = fileDao.insert(file);

		final Asset newAsset = new Asset();
		final String randomCode = generateID(5);
		newAsset.setAssetCode(randomCode);
		newAsset.setAssetName(asset.getAssetName());

		final AssetType assetType = assetTypeDao.getAssetTypeById(asset.getTypeId());
		newAsset.setAssetType(assetType);

		final AssetStatus assetStatus = assetStatusDao.getAssetStatusById(asset.getStatusId());
		newAsset.setAssetStatus(assetStatus);

		final Company company = companyDao.getCompanyById(asset.getCompanyId());
		newAsset.setCompany(company);
		newAsset.setFile(file);

		final InvoiceDetail detail = invoiceDetailDao.getInvDetailById(asset.getInvDetailId());
		newAsset.setInvoiceDetail(detail);
		newAsset.setCreatedBy(principalService.getPrincipal());

		assetDao.insert(newAsset);

		result.setId(newAsset.getId());
		result.setMessage("Insert Asset Success! " + newAsset.getAssetCode());
		return result;
	}

	@Override
	public List<AssetTypeResDto> getAllType() {
		final List<AssetTypeResDto> types = new ArrayList<>();

		assetTypeDao.getAll().forEach(t -> {
			final AssetTypeResDto type = new AssetTypeResDto();
			type.setId(t.getId());
			type.setTypeCode(t.getTypeCode());
			type.setTypeName(t.getTypeName());
			types.add(type);
		});

		return types;
	}

	@Transactional
	@Override
	public InsertResDto insertAssetStatus(AssetStatusInsertReqDto status) {
		InsertResDto result = new InsertResDto();

		AssetStatus newStatus = null;
		for (int i = 0; i < status.getStatusName().size(); i++) {
			newStatus = new AssetStatus();
			final String randomCode = generateID(5);
			newStatus.setStatusCode(randomCode);
			newStatus.setStatusName(status.getStatusName().get(i));
			newStatus.setCreatedBy(principalService.getPrincipal());

			assetStatusDao.insert(newStatus);
		}

		result.setId(newStatus.getId());
		result.setMessage("Asset Status Has Been Added");
		return result;
	}

	@Transactional
	@Override
	public InsertResDto insertAssetType(AssetTypeInsertReqDto type) {
		InsertResDto result = new InsertResDto();

		AssetType newType = null;

		for (int i = 0; i < type.getTypeName().size(); i++) {
			newType = new AssetType();
			newType.setTypeCode(type.getTypeCode().get(i));
			newType.setTypeName(type.getTypeName().get(i));
			newType.setCreatedBy(principalService.getPrincipal());

			assetTypeDao.insert(newType);
		}

		result.setId(newType.getId());
		result.setMessage("Asset Type Has Been Added");
		return result;
	}

	@Override
	public AssetResDto getByID(Long id) {
		final Asset myAsset = assetDao.getAssetById(id);
		
		final AssetResDto asset = new AssetResDto();
		asset.setId(myAsset.getId());
		asset.setFile(myAsset.getFile().getId());
		asset.setAssetCode(myAsset.getAssetCode());
		asset.setAssetName(myAsset.getAssetName());
		asset.setTypeName(myAsset.getAssetType().getTypeName());
		asset.setStatusId(myAsset.getAssetStatus().getId());
		asset.setStatusName(myAsset.getAssetStatus().getStatusName());
		asset.setCompanyName(myAsset.getCompany().getCompanyName());

		return asset;
	}

}
