package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseModel {
	
	@Column(name = "user_id_number", length = 20, unique = true, nullable = false)
	private String userIdNumber;
	
	@Column(name = "user_fullname", length = 50, nullable = false)
	private String userFullname;
	
	@Column(name = "user_gender", length = 6, nullable = false)
	private String userGender;
	
	@Column(name = "user_address", length = 80, nullable = false)
	private String userAddress;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	public String getUserIdNumber() {
		return userIdNumber;
	}
	
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	
	public String getUserFullname() {
		return userFullname;
	}
	
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}
	
	public String getUserGender() {
		return userGender;
	}
	
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

}
