package com.lawencon.assetsystem.constant;

public enum TypeCheckout {
	EMPLOYEE((long) 1, "Employee", "TY-0001"), GENERAL((long) 2, "Asset General", "TY-0002"), LOCATION((long) 3, "Location", "TY-0003");
	
	private final Long typeId;
	private final String typeCode;
	private final String typeName;
	
	private TypeCheckout(Long typeId, String typeCode, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeCode = typeCode;
	}

	public Long getTypeId() {
		return typeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeName() {
		return typeName;
	}
	
}