package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDao;
import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.repo.InvoiceRepo;

@Repository
@Profile("springdatajpa-query")
public class InvoiceDaoSpringDataJPAImpl implements InvoiceDao {

	private final InvoiceRepo invoiceRepo; 
	
	public InvoiceDaoSpringDataJPAImpl(InvoiceRepo invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

	@Override
	public Invoice insert(Invoice invoice) {
		invoiceRepo.save(invoice);
		return invoice;
	}

	@Override
	public List<Invoice> getAll() {
		final List<Invoice> invoices = invoiceRepo.findAll();
		return invoices;
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		final Invoice invoice = invoiceRepo.findById(id).get();
		return invoice;
	}

	@Override
	public Invoice getInvoiceByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
