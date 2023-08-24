package com.lawencon.assetsystem.dto.employee;

import javax.validation.constraints.NotBlank;

public class EmployeeInsertReqDto {

	@NotBlank(message = "employee name can't be empty!")
	private String employeeName;
	private Long companyId;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
