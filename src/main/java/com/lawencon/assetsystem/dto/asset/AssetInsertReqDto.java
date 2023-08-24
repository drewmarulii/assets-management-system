package com.lawencon.assetsystem.dto.asset;

public class AssetInsertReqDto {
	
	private String assetName;
	private Long typeId;
	private Long statusId;
	private Long companyId;
	private String file;
	private String fileExtension;
	private Long invDetailId;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Long getInvDetailId() {
		return invDetailId;
	}

	public void setInvDetailId(Long invDetailId) {
		this.invDetailId = invDetailId;
	}

}
