package com.lawencon.assetsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.assetsystem.dto.checkout.CheckinInsertReqDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutDetailResDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutInsertReqDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutResDto;
import com.lawencon.assetsystem.service.CheckinService;
import com.lawencon.assetsystem.service.CheckoutService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("checkouts")
@SecurityRequirement(name = "bearerAuth")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;
	
	@Autowired
	private CheckinService checkinService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CheckoutInsertReqDto checkout) {
		final InsertResDto result = checkoutService.insertCheckout(checkout);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CheckoutResDto>> getAll() {
		final List<CheckoutResDto> checkouts = checkoutService.getCheckoutCode();
		return new ResponseEntity<>(checkouts, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CheckoutResDto>> getAllCheckout() {
		final List<CheckoutResDto> checkouts = checkoutService.getAllCheckouts();
		return new ResponseEntity<>(checkouts, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<List<CheckoutDetailResDto>> getAllCheckoutDetail(@RequestParam("code") String checkoutCode) {
		final List<CheckoutDetailResDto> details = checkoutService.getCheckoutDetails(checkoutCode);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateCheckin(@RequestBody CheckinInsertReqDto checkin){
		final UpdateResDto result = checkinService.insertCheckin(checkin);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
