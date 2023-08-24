package com.lawencon.assetsystem.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.company.CompanyInsertReqDto;
import com.lawencon.assetsystem.dto.company.CompanyResDto;
import com.lawencon.assetsystem.dto.company.CompanyUpdateReqDto;
import com.lawencon.assetsystem.service.CompanyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("companies")
@SecurityRequirement(name = "bearerAuth")
public class CompanyController {

	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCompany(@RequestBody CompanyInsertReqDto company) {
		final InsertResDto result = companyService.insert(company);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CompanyResDto>> getAll() throws SQLException {
		final List<CompanyResDto> companies = companyService.getAll();
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<CompanyResDto> getById(@RequestParam("code") long id) {
		final CompanyResDto company = companyService.getById(id);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateCompany(@RequestBody CompanyUpdateReqDto data) {
		final UpdateResDto result = companyService.updateCompany(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
