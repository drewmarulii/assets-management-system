package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CheckoutTypeDao;
import com.lawencon.assetsystem.model.CheckoutType;

@Repository
@Profile("hql-query")
public class CheckoutTypeDaoHQLImpl implements CheckoutTypeDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CheckoutType> getAll() {
		final String sql = "SELECT "
				+ "	ct "
				+ "FROM "
				+ "	CheckoutType ct";
		final List<CheckoutType> coTypes = this.em.createQuery(sql, CheckoutType.class)
				.getResultList();
		
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
