package com.lawencon.assetsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.Checkout;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Long>{
	
	@Query(value = "SELECT DISTINCT ON (checkout_code)"
			+ "	* "
			+ "FROM "
			+ "	checkout c "
			+ "INNER JOIN "
			+ "	checkout_details cd ON cd.checkout_id = c.id "
			+ "WHERE "
			+ "	checkout_date IS NOT NULL "
			+ "	AND checkin_date IS NULL ", nativeQuery = true)
	List<Checkout> getCheckoutCode();
}
