package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Invoice;

public interface InvoiceDao {
	Invoice insert(Invoice invoice);
	List<Invoice> getAll();
	Invoice getInvoiceById(Long id);
	Invoice getInvoiceByCode(String code);
}
