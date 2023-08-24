package com.lawencon.assetsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long>{

	Integer removeById(Long id);
}
