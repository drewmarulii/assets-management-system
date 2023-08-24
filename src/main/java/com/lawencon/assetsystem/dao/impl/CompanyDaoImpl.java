package com.lawencon.assetsystem.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.model.Company;

@Repository
@Profile("native-query")
public class CompanyDaoImpl implements CompanyDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAll() {
		final String sql = "SELECT "
				+ "	* "
				+ "FROM "
				+ "	company c ";
		final List<Company> companies = this.em.createNativeQuery(sql, Company.class).getResultList();
		
		return companies;
	}

	@Override
	public Company insert(Company company) {
		em.persist(company);
		return company;
	}

	@Override
	public Company getCompanyById(Long id) {
		final Company company = this.em.find(Company.class, id);
		return company;
	}

}
