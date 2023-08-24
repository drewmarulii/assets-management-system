package com.lawencon.assetsystem.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.InvoiceDetailDao;
import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.model.InvoiceDetail;
import com.lawencon.assetsystem.model.Provider;

@Repository
@Profile("hql-query")
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
				+ "	i.invoice.invoiceCode, "
				+ "	i.itemName, "
				+ "	i.provider.providerName "
				+ "FROM "
				+ "	InvoiceDetail i "
				+ "WHERE "
				+ "	i.invoice.invoiceCode = :invoiceCode";
		
		final List<?> detailObjs = this.em.createQuery(sql)
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

	@Override
	public List<InvoiceDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
