package com.lawencon.assetsystem.dto.checkout;

import java.util.List;

public class CheckinInsertReqDto {

	private List<Long> detailId;

	public List<Long> getDetailId() {
		return detailId;
	}

	public void setDetailId(List<Long> detailId) {
		this.detailId = detailId;
	}
	
}
