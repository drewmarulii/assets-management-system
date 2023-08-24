package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier extends BaseModel{

	@Column(name = "supplier_code", length = 10, unique = true, nullable = false)
	private String supplierCode;
	
	@Column(name = "supplier_name", length = 50, nullable = false)
	private String supplierName;
	
	@Column(name = "supplier_phone", length = 15, nullable = false)
	private String supplierPhone;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	public String getSupplierCode() {
		return supplierCode;
	}
	
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getSupplierPhone() {
		return supplierPhone;
	}
	
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

}
