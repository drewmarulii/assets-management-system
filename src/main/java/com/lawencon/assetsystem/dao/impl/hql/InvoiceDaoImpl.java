package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDao;
import com.lawencon.assetsystem.model.Invoice;

@Repository
@Profile("hql-query")
public class InvoiceDaoImpl implements InvoiceDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Invoice insert(Invoice invoice) {
		em.persist(invoice);
		return invoice;
	}

	@Override
	public List<Invoice> getAll() {
		final String sql = "SELECT "
				+ "	i "
				+ "FROM "
				+ "	Invoice i";
		
		final List<Invoice> invoices = this.em.createQuery(sql, Invoice.class)
				.getResultList();
		
		return invoices;
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		final Invoice invoice = this.em.find(Invoice.class, id);
		return invoice;
	}

	@Override
	public Invoice getInvoiceByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
