package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.CheckoutDao;
import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dao.InvoiceDao;
import com.lawencon.assetsystem.dao.InvoiceDetailDao;
import com.lawencon.assetsystem.dao.ProviderDao;
import com.lawencon.assetsystem.dao.SupplierDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.invoicedetail.InvoiceDetailResDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceInsertReqDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceResDto;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.Invoice;
import com.lawencon.assetsystem.model.InvoiceDetail;
import com.lawencon.assetsystem.model.Provider;
import com.lawencon.assetsystem.model.Supplier;
import com.lawencon.assetsystem.service.InvoiceService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private final InvoiceDao invoiceDao;
	private final InvoiceDetailDao invoiceDetailDao;
	private final SupplierDao supplierDao;
	private final FileDao fileDao;
	private final ProviderDao providerDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public InvoiceServiceImpl(CheckoutDao checkoutDao, InvoiceDao invoiceDao, FileDao fileDao,
				InvoiceDetailDao invoiceDetailDao, SupplierDao supplierDao, ProviderDao providerDao,
				PrincipalService principalService) {
		this.invoiceDao = invoiceDao;
		this.invoiceDetailDao = invoiceDetailDao;
		this.supplierDao = supplierDao;
		this.fileDao = fileDao;
		this.providerDao = providerDao;
		this.principalService = principalService;
	}
	
	@Transactional
	@Override
	public InsertResDto insertInvoice(InvoiceInsertReqDto invoice) {

		InsertResDto result = new InsertResDto();
		final LocalDateTime now = LocalDateTime.now(); 
		
		File file = new File();
		file.setFile(invoice.getFile());
		file.setFileExtension(invoice.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		file = fileDao.insert(file);
				
		final Supplier supplier = supplierDao.getSupplierById(invoice.getSupplierId());
		
		Invoice newInvoice = new Invoice();
		final String randomCode = generateID(5);
		newInvoice.setFile(file);
		newInvoice.setSupplier(supplier);
		newInvoice.setInvoiceDate(now);
		newInvoice.setInvoiceCode(randomCode);
		
		newInvoice.setCreatedBy(principalService.getPrincipal());
		
		newInvoice = invoiceDao.insert(newInvoice);
		
		for (int i=0; i < invoice.getInvoiceDetails().size(); i++) {
			final InvoiceDetail detail = new InvoiceDetail();
			detail.setInvoice(newInvoice);
			final String randomCodeDetail = generateID(5);
			detail.setDetailCode(randomCodeDetail);
			detail.setItemName(invoice.getInvoiceDetails().get(i).getItemName());
			
			final Provider provider = providerDao.getProviderById(invoice.getInvoiceDetails().get(i).getProviderId());
			
			provider.setId(invoice.getInvoiceDetails().get(i).getProviderId());
			detail.setProvider(provider);
			detail.setCreatedBy(principalService.getPrincipal());
			
			invoiceDetailDao.insertInvoiceDetail(detail);
		}
		
		result.setId(newInvoice.getId());
		result.setMessage("Insert Invoice Success!");
		return result;
	}
	
	public List<InvoiceResDto> getAll() {
		final List<InvoiceResDto> invoices = new ArrayList<>();
		
		invoiceDao.getAll().forEach(i -> {
			final InvoiceResDto invoice = new InvoiceResDto();
			invoice.setId(i.getId());
			invoice.setInvoiceCode(i.getInvoiceCode());
			invoice.setInvoiceDate(i.getInvoiceDate().toString());
			invoice.setFileId(i.getFile().getId());
			invoice.setSupplierName(i.getSupplier().getSupplierName());
			invoices.add(invoice);
		});
		
		return invoices;
	}

	@Override
	public List<InvoiceDetailResDto> getDetailByInvoice(String code) {
		final Invoice invoice = new Invoice();
		invoice.setInvoiceCode(code);
		
		final List<InvoiceDetailResDto> details = new ArrayList<>();
				
		invoiceDetailDao.getDetailByInvoice(invoice).forEach(d -> {
			final InvoiceDetailResDto detail = new InvoiceDetailResDto();
			detail.setId(d.getId());
			detail.setFileId(d.getInvoice().getFile().getId());
			detail.setDetailCode(d.getDetailCode());
			detail.setItemName(d.getItemName());
			detail.setProviderName(d.getProvider().getProviderName());
			details.add(detail);
		});
		
		return details;
	}

	@Override
	public List<InvoiceDetailResDto> getAllDetail() {
		final List<InvoiceDetailResDto> invoices = new ArrayList<>();
		
		invoiceDetailDao.getAll().forEach(i -> {
			final InvoiceDetailResDto invoice = new InvoiceDetailResDto();
			invoice.setId(i.getId());
			invoice.setDetailCode(i.getDetailCode());
			invoice.setItemName(i.getItemName());
			invoice.setProviderName(i.getProvider().getProviderName());
			invoices.add(invoice);
		});
		
		return invoices;
	}

	@Override
	public InvoiceResDto getInvoiceByCode(String code) {
		final InvoiceResDto invoice = new InvoiceResDto();
		
		final Invoice myInvoice = invoiceDao.getInvoiceByCode(code);
		invoice.setId(myInvoice.getId());
		invoice.setFileId(myInvoice.getFile().getId());
		invoice.setInvoiceCode(myInvoice.getInvoiceCode());
		invoice.setInvoiceDate(myInvoice.getInvoiceDate().toString());
		
		return invoice;
	}

}
