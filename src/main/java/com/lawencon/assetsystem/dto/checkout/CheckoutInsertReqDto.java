package com.lawencon.assetsystem.dto.checkout;

import java.util.List;

public class CheckoutInsertReqDto {

	private Long employeeId;
	private Long locationId;
	private Long assetGeneralId;
	private Long typeId;
	private List<CheckoutDetailInsertReqDto> details;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getAssetGeneralId() {
		return assetGeneralId;
	}

	public void setAssetGeneralId(Long assetGeneralId) {
		this.assetGeneralId = assetGeneralId;
	}

	public List<CheckoutDetailInsertReqDto> getDetails() {
		return details;
	}

	public void setDetails(List<CheckoutDetailInsertReqDto> details) {
		this.details = details;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public Long getTypeId() {
		return typeId;
	}

}
