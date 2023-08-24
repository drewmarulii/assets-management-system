package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Company;

public interface CompanyDao {
	List<Company> getAll();
	Company insert(Company company);
	Company getCompanyById(Long id);
}
