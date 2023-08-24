package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "asset_status")
public class AssetStatus extends BaseModel {

	@Column(name = "status_code", length = 10, unique = true, nullable = false)
	private String statusCode;
	
	@Column(name = "status_name", length = 20, nullable = false)
	private String statusName;
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
