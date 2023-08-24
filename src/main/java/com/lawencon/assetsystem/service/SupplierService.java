package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.supplier.SupplierInsertReqDto;
import com.lawencon.assetsystem.dto.supplier.SupplierResDto;

public interface SupplierService {
	List<SupplierResDto> getAll(); 
	InsertResDto insert(SupplierInsertReqDto supplier);
}
