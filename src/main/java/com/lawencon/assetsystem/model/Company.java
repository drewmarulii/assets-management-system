package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BaseModel {

	@Column(name = "company_name", length = 50, unique = true, nullable = false)
	private String companyName;
	
	@Column(name = "company_address", length = 80, nullable = false)
	private String companyAddress;
	
	@Column(name = "company_phone", length = 13, nullable = false)
	private String companyPhone;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyAddress() {
		return companyAddress;
	}
	
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	public String getCompanyPhone() {
		return companyPhone;
	}
	
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	
}
