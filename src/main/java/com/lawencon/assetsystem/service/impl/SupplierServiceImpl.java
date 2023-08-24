package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dao.SupplierDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.supplier.SupplierInsertReqDto;
import com.lawencon.assetsystem.dto.supplier.SupplierResDto;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.Supplier;
import com.lawencon.assetsystem.service.PrincipalService;
import com.lawencon.assetsystem.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	private final SupplierDao supplierDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public SupplierServiceImpl(SupplierDao supplierDao, FileDao fileDao, PrincipalService principalService) {
		this.supplierDao = supplierDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}
	
	@Override
	public List<SupplierResDto> getAll() {
		final List<SupplierResDto> suppliers = new ArrayList<>();
		
		supplierDao.getAll().forEach(s -> {
			final SupplierResDto supplier = new SupplierResDto();
			supplier.setId(s.getId());
			supplier.setFileId(s.getFile().getId());
			supplier.setFile(s.getFile().getFile());
			supplier.setFileExtension(s.getFile().getFileExtension());
			supplier.setSupplierCode(s.getSupplierCode());
			supplier.setSupplierName(s.getSupplierName());
			supplier.setSupplierPhone(s.getSupplierPhone());
			suppliers.add(supplier);
		});
		
		return suppliers;
	}

	@Transactional
	@Override
	public InsertResDto insert(SupplierInsertReqDto supplier) {
		InsertResDto result = new InsertResDto();
		
		final Supplier newSupplier = new Supplier();
		final String randomCode = generateID(5);
		newSupplier.setSupplierCode(randomCode);
		newSupplier.setSupplierName(supplier.getSupplierName());
		newSupplier.setSupplierPhone(supplier.getSupplierPhone());
		
		File file = new File();
		file.setFile(supplier.getFile());
		file.setFileExtension(supplier.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		file = fileDao.insert(file);
				
		newSupplier.setFile(file);
		newSupplier.setCreatedBy(principalService.getPrincipal());
		supplierDao.insert(newSupplier);
		
		result.setId(newSupplier.getId());
		result.setMessage("Insert Supplier Success!");
		return result;
	}
	
}
