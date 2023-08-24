package com.lawencon.assetsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseModel  {

	@Column(name = "role_code", length = 5, unique = true, nullable = false)
	private String roleCode;
	
	@Column(name = "role_name", length = 20, nullable = false)
	private String roleName;
	
	public String getRoleCode() {
		return roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
