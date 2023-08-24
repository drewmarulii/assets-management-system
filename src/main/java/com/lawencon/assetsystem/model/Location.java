package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location extends BaseModel {

	@Column(name = "location_number", length = 10, unique = true, nullable = false)
	private String locationNumber;
	
	@Column(name = "location_detail", length = 50, nullable = false)
	private String locationDetail;
	
	public String getLocationNumber() {
		return locationNumber;
	}
	
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}
	
	public String getLocationDetail() {
		return locationDetail;
	}
	
	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

}
