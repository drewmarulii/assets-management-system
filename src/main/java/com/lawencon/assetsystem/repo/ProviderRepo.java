package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.Provider;

@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long>{

}
