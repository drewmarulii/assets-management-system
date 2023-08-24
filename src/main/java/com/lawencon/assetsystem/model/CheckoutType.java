package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_type")
public class CheckoutType extends BaseModel{

	@Column(name = "c_type_code", length = 10, unique = true, nullable = false)
	private String cTypeCode;
	
	@Column(name = "c_type_name", length = 10, nullable = false)
	private String cTypeName;
	
	public String getcTypeCode() {
		return cTypeCode;
	}
	
	public void setcTypeCode(String cTypeCode) {
		this.cTypeCode = cTypeCode;
	}
	
	public String getcTypeName() {
		return cTypeName;
	}
	
	public void setcTypeName(String cTypeName) {
		this.cTypeName = cTypeName;
	}
	
}
