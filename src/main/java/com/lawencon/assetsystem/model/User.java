package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class User extends BaseModel {
	
	@Column(name = "user_email", length = 50, unique = true, nullable = false)
	private String userEmail;
	
	@Column(name = "user_password", nullable = false)
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name = "profile_id")
	private UserProfile profile;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private UserRole role;
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public UserProfile getProfile() {
		return profile;
	}
	
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	} 

}