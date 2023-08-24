package com.lawencon.assetsystem.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.lawencon.assetsystem.model.CheckoutType;
import com.lawencon.assetsystem.model.Employee;
import com.lawencon.assetsystem.model.Location;

@Repository
@Profile("native-query")
public class CheckoutDaoImpl implements CheckoutDao {

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
				+ "	checkout_code, "
				+ "	ct.c_type_name, "
				+ "	e.employee_name, "
				+ "	l.location_detail, "
				+ "	a.asset_name,"
				+ "	c.id "
				+ "FROM "
				+ "	checkout c "
				+ "INNER JOIN "
				+ "	checkout_details cd ON cd.checkout_id = c.id "
				+ "INNER JOIN "
				+ "	checkout_type ct ON ct.id = c.type_id "
				+ "LEFT JOIN "
				+ "	employee e ON e.id = c.employee_id "
				+ "LEFT JOIN "
				+ "	location l ON l.id = c.location_id "
				+ "LEFT JOIN "
				+ "	assets a ON a.id = c.asset_id "
				+ "WHERE "
				+ "	checkout_date IS NOT NULL "
				+ "	AND checkin_date IS NULL "
				+ "GROUP BY "
				+ "	checkout_code, "
				+ "	ct.c_type_name, "
				+ "	e.employee_name, "
				+ "	l.location_detail, "
				+ "	a.asset_name, "
				+ "	c.id ";
		final List<?> checkoutObjs = this.em.createNativeQuery(sql).getResultList();
		
		if (checkoutObjs.size() > 0) {
			for (Object checkoutObj : checkoutObjs) {
				final Object[] checkoutArr = (Object[]) checkoutObj;
				final Checkout checkout = new Checkout();
				checkout.setCheckoutCode(checkoutArr[0].toString());
				
				final CheckoutType type = new CheckoutType();
				type.setcTypeName(checkoutArr[1].toString());
				checkout.setCheckoutType(type);
				
				final Employee employee = new Employee();
				if (checkoutArr[2] != null) {
					employee.setEmployeeName(checkoutArr[2].toString());
				}
				
				final Location location = new Location();
				if (checkoutArr[3] != null) {
					location.setLocationDetail(checkoutArr[3].toString());
				}
				
				final Asset general = new Asset();
				if (checkoutArr[4] != null) {
					general.setAssetName(checkoutArr[4].toString());
				}
				
				checkout.setId(Long.valueOf(checkoutArr[5].toString()));
				
				checkout.setEmployee(employee);
				checkout.setLocation(location);
				checkout.setAsset(general);
				checkouts.add(checkout);
			}
		}
		
		return checkouts;
	}

	@Override
	public List<CheckoutDetails> getDetailsByCode(Checkout checkout) {
		final List<CheckoutDetails> details = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		final String sql = "SELECT "
				+ "	cd.id AS detail_id, "
				+ "	cd.asset_id, "
				+ "	asset_name, "
				+ "	checkout_id, "
				+ " TO_CHAR(checkout_date, 'YYYY-MM-DD HH24:MI') AS checkout_date, "
				+ " TO_CHAR(due_date, 'YYYY-MM-DD HH24:MI') AS due_date, "
				+ " TO_CHAR(checkin_date, 'YYYY-MM-DD HH24:MI') AS checkin_date "
				+ "FROM "
				+ "	checkout_details cd "
				+ "INNER JOIN "
				+ "	checkout c ON c.id = cd.checkout_id "
				+ "INNER JOIN "
				+ "	assets a ON cd.asset_id = a.id "
				+ "WHERE "
				+ "	checkout_code = :checkoutCode";
		final List<?> detailObjs = this.em.createNativeQuery(sql)
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
				cdetail.setCheckoutDate(LocalDateTime.parse(detailArr[4].toString(), formatter));
				
				if (detailArr[5] != null) {
					cdetail.setDueDate(LocalDateTime.parse(detailArr[5].toString(), formatter));
				}
				
				if (detailArr[6] != null) {
					cdetail.setCheckinDate(LocalDateTime.parse(detailArr[6].toString(), formatter));
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
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	checkout";
		final List<Checkout> checkouts = this.em.createQuery(sql, Checkout.class)
				.getResultList();
		
		return checkouts;
	}

	@Override
	public List<Checkout> getAllCheckout() {
		final List<Checkout> checkouts = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	checkout_code, "
				+ "	ct.c_type_name, "
				+ "	e.employee_name, "
				+ "	l.location_detail, "
				+ "	a.asset_name,"
				+ "	c.id "
				+ "FROM "
				+ "	checkout c "
				+ "INNER JOIN "
				+ "	checkout_details cd ON cd.checkout_id = c.id "
				+ "INNER JOIN "
				+ "	checkout_type ct ON ct.id = c.type_id "
				+ "LEFT JOIN "
				+ "	employee e ON e.id = c.employee_id "
				+ "LEFT JOIN "
				+ "	location l ON l.id = c.location_id "
				+ "LEFT JOIN "
				+ "	assets a ON a.id = c.asset_id "
				+ "GROUP BY "
				+ "	checkout_code, "
				+ "	ct.c_type_name, "
				+ "	e.employee_name, "
				+ "	l.location_detail, "
				+ "	a.asset_name, "
				+ "	c.id";
		
		final List<?> checkoutObjs = this.em.createNativeQuery(sql).getResultList();
		
		if (checkoutObjs.size() > 0) {
			for (Object checkoutObj : checkoutObjs) {
				final Object[] checkoutArr = (Object[]) checkoutObj;
				final Checkout checkout = new Checkout();
				checkout.setCheckoutCode(checkoutArr[0].toString());
				
				final CheckoutType type = new CheckoutType();
				type.setcTypeName(checkoutArr[1].toString());
				checkout.setCheckoutType(type);
				
				final Employee employee = new Employee();
				if (checkoutArr[2] != null) {
					employee.setEmployeeName(checkoutArr[2].toString());
				}
				
				final Location location = new Location();
				if (checkoutArr[3] != null) {
					location.setLocationDetail(checkoutArr[3].toString());
				}
				
				final Asset general = new Asset();
				if (checkoutArr[4] != null) {
					general.setAssetName(checkoutArr[4].toString());
				}
				
				checkout.setId(Long.valueOf(checkoutArr[5].toString()));
				
				checkout.setEmployee(employee);
				checkout.setLocation(location);
				checkout.setAsset(general);
				checkouts.add(checkout);
			}
		}
		
		return checkouts;
	}
}
