package com.lawencon.assetsystem.dto.checkouttype;

import java.util.List;

public class CheckoutTypeInsertReqDto {

	private List<String> typeCode;
	private List<String> typeName;

	public List<String> getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(List<String> typeCode) {
		this.typeCode = typeCode;
	}
	
	public List<String> getTypeName() {
		return typeName;
	}

	public void setTypeName(List<String> typeName) {
		this.typeName = typeName;
	}
}
