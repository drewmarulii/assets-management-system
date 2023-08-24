package com.lawencon.assetsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.supplier.SupplierInsertReqDto;
import com.lawencon.assetsystem.dto.supplier.SupplierResDto;
import com.lawencon.assetsystem.service.SupplierService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("suppliers")
@SecurityRequirement(name = "bearerAuth")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertSupplier(@RequestBody SupplierInsertReqDto supplier) {
		final InsertResDto result = supplierService.insert(supplier);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<SupplierResDto>> getAll() {
		final List<SupplierResDto> result = supplierService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
