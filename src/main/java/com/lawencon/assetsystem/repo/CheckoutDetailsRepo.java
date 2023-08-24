package com.lawencon.assetsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.CheckoutDetails;

@Repository
public interface CheckoutDetailsRepo extends JpaRepository<CheckoutDetails, Long>{
	
	@Query(value = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	checkout_details cd "
				+ "INNER JOIN "
				+ "	checkout c ON c.id = cd.checkout_id "
				+ "INNER JOIN "
				+ "	assets a ON cd.asset_id = a.id "
				+ "WHERE "
				+ "	checkout_code = :checkoutCode "
				+ "	AND checkin_date IS NULL", nativeQuery = true)
	List<CheckoutDetails> getByCheckoutCheckoutCode(@Param(value = "checkoutCode") String checkoutCode);
}
