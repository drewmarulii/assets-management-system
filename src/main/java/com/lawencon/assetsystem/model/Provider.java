package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
public class Provider extends BaseModel{

	@Column(name = "provider_code", length = 10, unique = true, nullable = false)
	private String providerCode;
	
	@Column(name = "provider_name", length = 50, nullable = false)
	private String providerName;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	public String getProviderCode() {
		return providerCode;
	}
	
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	
	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
}
