package com.lawencon.assetsystem.service;

import java.util.List;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.invoicedetail.InvoiceDetailResDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceInsertReqDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceResDto;

public interface InvoiceService {
	InsertResDto insertInvoice(InvoiceInsertReqDto invoice);
	List<InvoiceResDto> getAll(); 
	List<InvoiceDetailResDto> getAllDetail();
	List<InvoiceDetailResDto> getDetailByInvoice(String code);
	InvoiceResDto getInvoiceByCode(String code);
}
