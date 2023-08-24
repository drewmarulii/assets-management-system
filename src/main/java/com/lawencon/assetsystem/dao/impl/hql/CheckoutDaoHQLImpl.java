package com.lawencon.assetsystem.dao.impl.hql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CheckoutDao;
import com.lawencon.assetsystem.model.Asset;
import com.lawencon.assetsystem.model.Checkout;
import com.lawencon.assetsystem.model.CheckoutDetails;

@Repository
@Profile("hql-query")
public class CheckoutDaoHQLImpl implements CheckoutDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Checkout insert(Checkout checkout) {
		em.persist(checkout);
		return checkout;
	}

	@Override
	public CheckoutDetails insertDetails(CheckoutDetails checkoutDetails) {
		em.persist(checkoutDetails);
		return checkoutDetails;
	}

	@Override
	public List<Checkout> getCheckoutCode() {
		final List<Checkout> checkouts = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	c.checkoutCode "
				+ "FROM "
				+ "	Checkout c "
				+ "INNER JOIN "
				+ "	CheckoutDetails cd ON cd.checkout.id = c.id "
				+ "WHERE "
				+ "	cd.checkoutDate IS NOT NULL "
				+ "	AND cd.checkinDate IS NULL "
				+ "GROUP BY "
				+ "	c.checkoutCode";
		final List<?> checkoutObjs = this.em.createQuery(sql)
				.getResultList();
		
		if (checkoutObjs.size() > 0) {
			for (Object checkoutObj : checkoutObjs) {
				final Object code = (Object) checkoutObj;
				final Checkout checkout = new Checkout();
				checkout.setCheckoutCode(code.toString());
				checkouts.add(checkout);
			}
		}
		
		return checkouts;
	}

	@Override
	public List<CheckoutDetails> getDetailsByCode(Checkout checkout) {
		final List<CheckoutDetails> details = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	cd.id AS detail_id, "
				+ "	cd.asset.id, "
				+ "	cd.asset.assetName, "
				+ "	cd.checkout.id, "
				+ " cd.checkoutDate, "
				+ " cd.dueDate "
				+ "FROM "
				+ "	CheckoutDetails cd "
				+ "WHERE "
				+ "	cd.checkout.checkoutCode = :checkoutCode "
				+ "	AND cd.checkinDate IS NULL";
		final List<?> detailObjs = this.em.createQuery(sql)
				.setParameter("checkoutCode", checkout.getCheckoutCode())
				.getResultList();
		
		if (detailObjs.size() > 0) {
			for (Object detailObj : detailObjs) {
				final Object[] detailArr = (Object[]) detailObj;
				final CheckoutDetails cdetail = new CheckoutDetails();
				cdetail.setId(Long.valueOf(detailArr[0].toString()));
				
				final Asset asset = new Asset();
				asset.setId(Long.valueOf(detailArr[1].toString()));
				asset.setAssetName(detailArr[2].toString());
				cdetail.setAsset(asset);

				final Checkout checkoutid = new Checkout();
				checkoutid.setId(Long.valueOf(detailArr[3].toString()));
				cdetail.setCheckoutDate(LocalDateTime.parse(detailArr[4].toString()));
				
				if (detailArr[5] != null) {
					cdetail.setDueDate(LocalDateTime.parse(detailArr[5].toString()));
				}
					
				cdetail.setCheckout(checkout);
	
				details.add(cdetail);
			}
		}
		
		return details;
	}

	@Override
	public Checkout getCheckoutById(Long checkoutid) {
		final Checkout checkout = this.em.find(Checkout.class, checkoutid);
		return checkout;
	}
	
	public CheckoutDetails getDetailById(Long detailid) {
		final CheckoutDetails detail = this.em.find(CheckoutDetails.class, detailid);
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
