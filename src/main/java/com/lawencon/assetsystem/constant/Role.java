	package com.lawencon.assetsystem.constant;

public enum Role {
	ADMIN("Super Admin", "R001"), FINANCE("Finance", "R002"), HR("HR", "R003"), SUPPORT("Support", "R004");
	
	private final String roleName;
	private final String roleCode;
	
	private Role(String roleName, String roleCode) {
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}
	
}