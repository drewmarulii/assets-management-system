package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.model.InvoiceDetail;

public interface InvoiceDetailDao {
	List<InvoiceDetail> getAll();
	InvoiceDetail insertInvoiceDetail(InvoiceDetail invoiceDetail);
	List<InvoiceDetail> getDetailByInvoice(Invoice invoice);
	InvoiceDetail getInvDetailById(Long id);
}
