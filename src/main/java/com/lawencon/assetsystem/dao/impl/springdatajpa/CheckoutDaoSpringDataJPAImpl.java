package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CheckoutDao;
import com.lawencon.assetsystem.model.Checkout;
import com.lawencon.assetsystem.model.CheckoutDetails;
import com.lawencon.assetsystem.repo.CheckoutDetailsRepo;
import com.lawencon.assetsystem.repo.CheckoutRepo;

@Repository
@Profile("springdatajpa-query")
public class CheckoutDaoSpringDataJPAImpl implements CheckoutDao {
	
	private final CheckoutRepo checkoutRepo;
	private final CheckoutDetailsRepo checkoutDetailRepo;

	public CheckoutDaoSpringDataJPAImpl(CheckoutRepo checkouRepo, CheckoutDetailsRepo checkoutDetailRepo) {
		this.checkoutRepo = checkouRepo;
		this.checkoutDetailRepo = checkoutDetailRepo;
	}

	@Override
	public Checkout insert(Checkout checkout) {
		checkoutRepo.save(checkout);
		return checkout;
	}

	@Override
	public CheckoutDetails insertDetails(CheckoutDetails checkoutDetails) {
		checkoutDetailRepo.save(checkoutDetails);
		return checkoutDetails;
	}

	@Override
	public List<Checkout> getCheckoutCode() {
		final List<Checkout> checkouts = checkoutRepo.getCheckoutCode();
		return checkouts;
	}

	@Override
	public List<CheckoutDetails> getDetailsByCode(Checkout checkout) {
		final List<CheckoutDetails> details = checkoutDetailRepo.getByCheckoutCheckoutCode(checkout.getCheckoutCode());
		return details;
	}

	@Override
	public Checkout getCheckoutById(Long checkoutid) {
		final Checkout checkout = checkoutRepo.findById(checkoutid).get();
		return checkout;
	}
	
	public CheckoutDetails getDetailById(Long detailid) {
		final CheckoutDetails detail = checkoutDetailRepo.findById(detailid).get();
		return detail;
	}

	@Override
	public List<Checkout> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Checkout> getAllCheckout() {
		// TODO Auto-generated method stub
		return null;
	}
}
