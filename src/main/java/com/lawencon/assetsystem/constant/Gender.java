package com.lawencon.assetsystem.constant;

public enum Gender {
	MALE("Male"), FEMALE("Female");
	
	private final String genderName;
	
	private Gender(String genderName) {
		this.genderName = genderName;
	}

	public String getGenderName() {
		return genderName;
	}
}