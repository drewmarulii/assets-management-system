package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDetailDao;
import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.model.InvoiceDetail;
import com.lawencon.assetsystem.repo.InvoiceDetailRepo;

@Repository
@Profile("springdatajpa-query")
public class InvoiceDetailDaoSpringDataJPAImpl implements InvoiceDetailDao {
	
	private final InvoiceDetailRepo invoiceDetailRepo;
	
	public InvoiceDetailDaoSpringDataJPAImpl(InvoiceDetailRepo invoiceDetailRepo) {
		this.invoiceDetailRepo = invoiceDetailRepo;
	}

	@Override
	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail) {
		invoiceDetailRepo.save(invoiceDetail);
		return invoiceDetail;
	}

	@Override
	public List<InvoiceDetail> getDetailByInvoice(Invoice invoice) {
		final List<InvoiceDetail> details = invoiceDetailRepo.getByInvoiceInvoiceCode(invoice.getInvoiceCode());
		return details;
	}

	@Override
	public InvoiceDetail getInvDetailById(Long id) {
		InvoiceDetail detail = invoiceDetailRepo.findById(id).get();
		return detail;
	}

	@Override
	public List<InvoiceDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
