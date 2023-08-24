package com.lawencon.assetsystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_details")
public class CheckoutDetails extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "checkout_id")
	private Checkout checkout;
	
	@Column(name = "checkout_date", nullable = false)
	private LocalDateTime checkoutDate;

	@Column(name = "due_date")
	private LocalDateTime dueDate;
	
	@Column(name = "checkin_date")
	private LocalDateTime checkinDate;
	
	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public Checkout getCheckout() {
		return checkout;
	}
	
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}
	
	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}
	
	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public LocalDateTime getCheckinDate() {
		return checkinDate;
	}
	
	public void setCheckinDate(LocalDateTime checkinDate) {
		this.checkinDate = checkinDate;
	}
	
}
