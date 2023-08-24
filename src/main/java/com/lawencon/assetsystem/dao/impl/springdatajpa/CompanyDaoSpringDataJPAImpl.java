package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.model.Company;
import com.lawencon.assetsystem.repo.CompanyRepo;

@Repository
@Profile("springdatajpa-query")
public class CompanyDaoSpringDataJPAImpl implements CompanyDao {
	
	private final CompanyRepo companyRepo;

	public CompanyDaoSpringDataJPAImpl(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

	@Override
	public List<Company> getAll() {
		final List<Company> companies = companyRepo.findAll();
		return companies;
	}

	@Override
	public Company insert(Company company) {
		companyRepo.save(company);
		return company;
	}

	@Override
	public Company getCompanyById(Long id) {
		final Company company = companyRepo.findById(id).get();
		return company;
	}

}
