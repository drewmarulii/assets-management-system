package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.CheckoutType;

public interface CheckoutTypeDao {
	List<CheckoutType> getAll(); 
	CheckoutType insert(CheckoutType checkoutType);
	CheckoutType getCheckoutTypeById(Long detailid);
}
