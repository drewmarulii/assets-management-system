package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.CompanyDao;
import com.lawencon.assetsystem.model.Company;

@Repository
@Profile("hql-query")
public class CompanyDaoHQLImpl implements CompanyDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Company> getAll() {
		final String sql = "SELECT "
				+ "	c "
				+ "FROM "
				+ "	Company c ";
		final List<Company> companies = this.em.createQuery(sql, Company.class)
				.getResultList();
		
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
