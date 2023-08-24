package com.lawencon.assetsystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDetailDao;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.model.InvoiceDetail;
import com.lawencon.assetsystem.model.Provider;

@Repository
@Profile("native-query")
public class InvoiceDetailDaoImpl implements InvoiceDetailDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail) {
		em.persist(invoiceDetail);
		return invoiceDetail;
	}

	@Override
	public List<InvoiceDetail> getDetailByInvoice(Invoice invoice) {
		final List<InvoiceDetail> details = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	i.id AS inv_detail_id, "
				+ "	detail_code, "
				+ "	item_name, "
				+ "	provider_name, "
				+ "	iv.file_id "
				+ "FROM "
				+ "	invoice_detail i "
				+ "INNER JOIN "
				+ "	invoices iv ON iv.id = i.invoice_id "
				+ "INNER JOIN "
				+ "	provider p ON p.id = i.provider_id "
				+ "WHERE "
				+ "	invoice_code = :invoiceCode";
		
		final List<?> detailObjs = this.em.createNativeQuery(sql)
				.setParameter("invoiceCode", invoice.getInvoiceCode())
				.getResultList();
		
		if (detailObjs.size() > 0) {
			for (Object detailObj : detailObjs) {
				final Object[] detailArr = (Object[]) detailObj;
				final InvoiceDetail detail = new InvoiceDetail();
				detail.setId(Long.valueOf(detailArr[0].toString()));
				detail.setDetailCode(detailArr[1].toString());
				detail.setItemName(detailArr[2].toString());
				
				final Provider provider = new Provider();
				provider.setProviderName(detailArr[3].toString());
				detail.setProvider(provider);
				
				final Invoice invoiceId = new Invoice();
				final File file = new File();
				file.setId(Long.valueOf(detailArr[4].toString()));
				invoiceId.setFile(file);
				detail.setInvoice(invoiceId);
				
				details.add(detail);
			}
		}
		
		return details;
	}

	@Override
	public InvoiceDetail getInvDetailById(Long id) {
		InvoiceDetail detail = this.em.find(InvoiceDetail.class, id);
		return detail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceDetail> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	invoice_detail i ";
		final List<InvoiceDetail> invoices = this.em.createNativeQuery(sql, InvoiceDetail.class).getResultList();

		return invoices;
	}
}
