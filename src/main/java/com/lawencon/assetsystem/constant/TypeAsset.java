package com.lawencon.assetsystem.constant;

public enum TypeAsset {
	GENERAL((long) 1, "General", "AT01"), LICENSES((long) 2, "Licenses", "AT02"), COMPONENTS((long) 3, "Components", "AT03"), CONSUMABLE((long) 3, "Consumable", "AT04");
	
	private final Long typeId;
	private final String typeCode;
	private final String typeName;
	
	private TypeAsset(Long typeId, String typeName, String typeCode) {
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