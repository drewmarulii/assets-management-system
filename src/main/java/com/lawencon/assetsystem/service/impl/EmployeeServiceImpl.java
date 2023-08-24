package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.dao.EmployeeDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.employee.EmployeeInsertReqDto;
import com.lawencon.assetsystem.dto.employee.EmployeeResDto;
import com.lawencon.assetsystem.model.Company;
import com.lawencon.assetsystem.model.Employee;
import com.lawencon.assetsystem.service.EmployeeService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDao employeeDao;
	private final CompanyDao companyDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public EmployeeServiceImpl(EmployeeDao employeeDao, CompanyDao companyDao, PrincipalService principalService) {
		this.employeeDao = employeeDao;
		this.companyDao = companyDao;
		this.principalService = principalService;
	}

	@Override
	public List<EmployeeResDto> getAll() {
		final List<EmployeeResDto> employees = new ArrayList<>();
		
		employeeDao.getAll().forEach(e -> {
			final EmployeeResDto employee = new EmployeeResDto();
			employee.setId(e.getId());
			employee.setEmployeeCode(e.getEmployeeCode());
			employee.setEmployeeName(e.getEmployeeName());
			employee.setCompanyName(e.getCompany().getCompanyName());
			employees.add(employee);
		});
		
		return employees;
	}

	@Transactional
	@Override
	public InsertResDto insert(EmployeeInsertReqDto employee) {
		
		InsertResDto result = new InsertResDto();
		
		final Employee newEmployee = new Employee();
		final String randomCode = generateID(5);
		newEmployee.setEmployeeCode(randomCode);
		newEmployee.setEmployeeName(employee.getEmployeeName());
		
		final Company company = companyDao.getCompanyById(employee.getCompanyId());
		newEmployee.setCompany(company);
		newEmployee.setCreatedBy(principalService.getPrincipal());
		employeeDao.insert(newEmployee);
		
		result.setId(newEmployee.getId());
		result.setMessage("Insert Employee Success!");
		return result;
	}
}
