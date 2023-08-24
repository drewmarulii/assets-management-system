package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset extends BaseModel {
	
	@Column(name = "asset_code", length = 10, unique = true, nullable = false)
	private String assetCode;
	
	@Column(name = "asset_name", length = 50, nullable = false)
	private String assetName;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private AssetType assetType;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private AssetStatus assetStatus;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "inv_detail_id")
	private InvoiceDetail invoiceDetail;
	
	public String getAssetCode() {
		return assetCode;
	}
	
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
	public String getAssetName() {
		return assetName;
	}
	
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	public AssetType getAssetType() {
		return assetType;
	}
	
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	
	public AssetStatus getAssetStatus() {
		return assetStatus;
	}
	
	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}
	
	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	
}
