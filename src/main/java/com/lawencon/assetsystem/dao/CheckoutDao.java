package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Checkout;
import com.lawencon.assetsystem.model.CheckoutDetails;

public interface CheckoutDao {
	Checkout insert(Checkout checkout);
	List<Checkout> getAll();
	List<Checkout> getAllCheckout();
	CheckoutDetails insertDetails(CheckoutDetails checkoutDetails);
	List<Checkout> getCheckoutCode();
	List<CheckoutDetails> getDetailsByCode(Checkout checkout);
	Checkout getCheckoutById(Long checkoutid);
	CheckoutDetails getDetailById(Long detailid);
}
