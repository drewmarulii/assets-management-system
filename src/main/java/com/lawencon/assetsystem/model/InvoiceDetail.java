package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail extends BaseModel{

	@Column(name = "detail_code", length = 10, unique = true, nullable = false)
	private String detailCode;
	
	@Column(name = "item_name", length = 50, nullable = false)
	private String itemName;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
	
	public String getDetailCode() {
		return detailCode;
	}
	
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
