package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.EmployeeDao;
import com.lawencon.assetsystem.model.Employee;

@Repository
@Profile("hql-query")
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Employee> getAll() {
		final String sql = "SELECT "
				+ "	e "
				+ "FROM "
				+ "	Employee e";
		final List<Employee> employees = this.em.createQuery(sql, Employee.class)
				.getResultList();
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		final Employee employee = this.em.find(Employee.class, id);
		return employee;
	}

	@Override
	public Employee insert(Employee employee) {
		em.persist(employee);
		return employee;
	}
}
