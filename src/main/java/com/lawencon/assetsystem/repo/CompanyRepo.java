package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

}
