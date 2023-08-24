package com.lawencon.assetsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.invoicedetail.InvoiceDetailResDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceInsertReqDto;
import com.lawencon.assetsystem.dto.invoices.InvoiceResDto;
import com.lawencon.assetsystem.service.InvoiceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("invoices")
@SecurityRequirement(name = "bearerAuth")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody InvoiceInsertReqDto invoice) {
		final InsertResDto result = invoiceService.insertInvoice(invoice);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<InvoiceResDto>> getAll() {
		final List<InvoiceResDto> invoices = invoiceService.getAll();
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<InvoiceResDto> getInvoiceByCode(@RequestParam String code) {
		final InvoiceResDto invoice = invoiceService.getInvoiceByCode(code);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}
	
	@GetMapping("/detail-all")
	public ResponseEntity<List<InvoiceDetailResDto>> getAllInvDetail() {
		final List<InvoiceDetailResDto> details = invoiceService.getAllDetail();
		return new ResponseEntity<>(details, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<List<InvoiceDetailResDto>> getAllInvDetail(@RequestParam String code) {
		final List<InvoiceDetailResDto> details = invoiceService.getDetailByInvoice(code);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}
}
