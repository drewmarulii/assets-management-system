package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.EmployeeDao;
import com.lawencon.assetsystem.model.Employee;
import com.lawencon.assetsystem.repo.EmployeeRepo;

@Repository
@Profile("springdatajpa-query")
public class EmployeeDaoSpringDataJPAImpl implements EmployeeDao {
	
	private final EmployeeRepo employeeRepo;
	
	public EmployeeDaoSpringDataJPAImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public List<Employee> getAll() {
		final List<Employee> employees = employeeRepo.findAll();
		return employees;
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		final Employee employee = employeeRepo.findById(id).get();
		return employee;
	}

	@Override
	public Employee insert(Employee employee) {
		employeeRepo.save(employee);
		return employee;
	}
}
