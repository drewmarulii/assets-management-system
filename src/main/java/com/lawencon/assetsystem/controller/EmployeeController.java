package com.lawencon.assetsystem.controller;

import java.sql.SQLException;
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
import com.lawencon.assetsystem.dto.employee.EmployeeInsertReqDto;
import com.lawencon.assetsystem.dto.employee.EmployeeResDto;
import com.lawencon.assetsystem.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("employees")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertEmployee(@RequestBody EmployeeInsertReqDto employee) {
		final InsertResDto response = employeeService.insert(employee);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResDto>> getAll() throws SQLException {
		final List<EmployeeResDto> employees = employeeService.getAll();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
}
