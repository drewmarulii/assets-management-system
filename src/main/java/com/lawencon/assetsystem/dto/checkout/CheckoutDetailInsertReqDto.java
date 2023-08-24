package com.lawencon.assetsystem.dto.checkout;

public class CheckoutDetailInsertReqDto {

	private Long assetId;
	private String dueDate;

	public Long getAssetId() {
		return assetId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
