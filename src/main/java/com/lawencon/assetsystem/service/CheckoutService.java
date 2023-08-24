package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutDetailResDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutInsertReqDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutResDto;
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeInsertReqDto;
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeResDto;

public interface CheckoutService {
	InsertResDto insertCheckout(CheckoutInsertReqDto checkout);
	List<CheckoutResDto> getCheckoutCode();
	List<CheckoutResDto> getAllCheckouts();
	List<CheckoutResDto> getAll();
	List<CheckoutDetailResDto> getCheckoutDetails(String courseid);
	InsertResDto insertCheckoutType(CheckoutTypeInsertReqDto type);
	List<CheckoutTypeResDto> getAllCheckoutType();
}
