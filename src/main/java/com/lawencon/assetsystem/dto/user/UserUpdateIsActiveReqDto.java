package com.lawencon.assetsystem.dto.user;

public class UserUpdateIsActiveReqDto {

	private Long userId;
	private Boolean isActive;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
