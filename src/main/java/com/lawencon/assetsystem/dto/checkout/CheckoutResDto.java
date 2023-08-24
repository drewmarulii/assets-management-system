package com.lawencon.assetsystem.dto.checkout;

public class CheckoutResDto {

	private Long id;
	private String checkoutCode;
	private String typeName;
	private String employeeName;
	private String locationName;
	private String assetGeneral;
		
	public String getCheckoutCode() {
		return checkoutCode;
	}

	public void setCheckoutCode(String checkoutCode) {
		this.checkoutCode = checkoutCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAssetGeneral() {
		return assetGeneral;
	}

	public void setAssetGeneral(String assetGeneral) {
		this.assetGeneral = assetGeneral;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
