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
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeInsertReqDto;
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeResDto;
import com.lawencon.assetsystem.service.CheckoutService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("checkouttypes")
@SecurityRequirement(name = "bearerAuth")
public class CheckoutTypeController {

	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CheckoutTypeInsertReqDto type) {
		final InsertResDto result = checkoutService.insertCheckoutType(type);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CheckoutTypeResDto>> getAll() {
		final List<CheckoutTypeResDto> types = checkoutService.getAllCheckoutType();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
}
