package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CheckoutTypeDao;
import com.lawencon.assetsystem.model.CheckoutType;
import com.lawencon.assetsystem.repo.CheckoutTypeRepo;

@Repository
@Profile("springdatajpa-query")
public class CheckoutTypeDaoSpringDataJPAImpl implements CheckoutTypeDao {

	private final CheckoutTypeRepo checkoutTypeRepo;
	
	public CheckoutTypeDaoSpringDataJPAImpl(CheckoutTypeRepo checkoutTypeRepo) {
		this.checkoutTypeRepo = checkoutTypeRepo;
	}

	@Override
	public List<CheckoutType> getAll() {
		final List<CheckoutType> coTypes = checkoutTypeRepo.findAll();
		return coTypes;
	}
	
	@Override
	public CheckoutType getCheckoutTypeById(Long detailid) {
		final CheckoutType detail = checkoutTypeRepo.findById(detailid).get();
		return detail;
	}

	@Override
	public CheckoutType insert(CheckoutType checkoutType) {
		checkoutTypeRepo.save(checkoutType);
		return checkoutType;
	}
}
