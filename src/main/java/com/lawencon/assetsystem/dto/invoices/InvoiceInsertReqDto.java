package com.lawencon.assetsystem.dto.invoices;

import java.util.List;

import com.lawencon.assetsystem.dto.invoicedetail.InvoiceDetailInsertReqDto;

public class InvoiceInsertReqDto {

	private String file;
	private String fileExtension;
	private Long supplierId;
	private List<InvoiceDetailInsertReqDto> invoiceDetails;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public List<InvoiceDetailInsertReqDto> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetailInsertReqDto> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

}
