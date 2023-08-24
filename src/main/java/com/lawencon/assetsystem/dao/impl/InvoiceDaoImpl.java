package com.lawencon.assetsystem.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.Invoice;

@Repository
@Profile("native-query")
public class InvoiceDaoImpl implements InvoiceDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Invoice insert(Invoice invoice) {
		em.persist(invoice);
		return invoice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	invoices i ";
		final List<Invoice> invoices = this.em.createNativeQuery(sql, Invoice.class).getResultList();

		return invoices;
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		final Invoice invoice = this.em.find(Invoice.class, id);
		return invoice;
	}

	@Override
	public Invoice getInvoiceByCode(String code) {
		final String sql = "SELECT "
				+ "	id, "
				+ " invoice_code,"
				+ "	TO_CHAR(invoice_date, 'YYYY-MM-DD HH24:MI') AS inv_date, "
				+ " file_id "
				+ "FROM "
				+ "	invoices i "
				+ "WHERE "
				+ "	invoice_code = :invoiceCode";
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		final Object invObj = this.em.createNativeQuery(sql)
				.setParameter("invoiceCode", code)
				.getSingleResult();
		
		final Object[] invArr = (Object[]) invObj;
		
		Invoice invoice = null;
		
		if (invArr.length > 0) {
			invoice = new Invoice();
			invoice.setId(Long.valueOf(invArr[0].toString()));
			invoice.setInvoiceCode(invArr[1].toString());
			invoice.setInvoiceDate(LocalDateTime.parse(invArr[2].toString(), formatter));
			
			final File file = new File();
			file.setId(Long.valueOf(invArr[3].toString()));
			invoice.setFile(file);
		}

		return invoice;
	}

}
