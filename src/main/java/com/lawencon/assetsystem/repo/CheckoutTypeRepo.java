package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.CheckoutType;

@Repository
public interface CheckoutTypeRepo extends JpaRepository<CheckoutType, Long>{

}
