package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.employee.EmployeeInsertReqDto;
import com.lawencon.assetsystem.dto.employee.EmployeeResDto;

public interface EmployeeService {
	List<EmployeeResDto> getAll(); 
	InsertResDto insert(EmployeeInsertReqDto employee);
}
