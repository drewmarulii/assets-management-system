package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Employee;

public interface EmployeeDao {
	List<Employee> getAll(); 
	Employee getEmployeeById(Long id);
	Employee insert(Employee employee);
}
