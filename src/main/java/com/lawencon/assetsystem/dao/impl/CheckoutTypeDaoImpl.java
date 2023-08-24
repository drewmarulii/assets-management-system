package com.lawencon.assetsystem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CheckoutTypeDao;
import com.lawencon.assetsystem.model.CheckoutType;

@Repository
@Profile("native-query")
public class CheckoutTypeDaoImpl implements CheckoutTypeDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckoutType> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	checkout_type";
		final List<CheckoutType> coTypes = this.em.createNativeQuery(sql, CheckoutType.class).getResultList();
		return coTypes;
		
	}
	
	@Override
	public CheckoutType getCheckoutTypeById(Long detailid) {
		final CheckoutType detail = this.em.find(CheckoutType.class, detailid);
		return detail;
	}

	@Override
	public CheckoutType insert(CheckoutType checkoutType) {
		em.persist(checkoutType);
		return checkoutType;
	}
}
