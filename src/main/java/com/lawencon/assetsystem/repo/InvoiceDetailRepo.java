package com.lawencon.assetsystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.InvoiceDetail;

@Repository
public interface InvoiceDetailRepo extends JpaRepository<InvoiceDetail, Long>{

	@Query(value = "SELECT "
			+ "	* "
			+ "FROM "
			+ "	invoice_detail i "
			+ "INNER JOIN "
			+ "	invoices iv ON iv.id = i.invoice_id "
			+ "INNER JOIN "
			+ "	provider p ON p.id = i.provider_id "
			+ "WHERE "
			+ "	invoice_code = :invoiceCode", nativeQuery = true)
	List<InvoiceDetail> getByInvoiceInvoiceCode(@Param(value = "invoiceCode") String invoiceCode);
}
