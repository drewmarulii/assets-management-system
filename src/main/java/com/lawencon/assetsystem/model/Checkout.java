package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout")
public class Checkout extends BaseModel {

	@Column(name = "checkout_code", length = 10, unique = true, nullable = false)
	private String checkoutCode;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = true)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "location_id", nullable = true)
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "asset_id", nullable = true)
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private CheckoutType checkoutType;
	
	public String getCheckoutCode() {
		return checkoutCode;
	}
	
	public void setCheckoutCode(String checkoutCode) {
		this.checkoutCode = checkoutCode;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public CheckoutType getCheckoutType() {
		return checkoutType;
	}
	
	public void setCheckoutType(CheckoutType checkoutType) {
		this.checkoutType = checkoutType;
	}

}
