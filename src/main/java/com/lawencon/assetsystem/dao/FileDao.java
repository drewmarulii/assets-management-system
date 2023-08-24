package com.lawencon.assetsystem.dao;

import com.lawencon.assetsystem.model.File;

public interface FileDao {
	File getByID(Long id) ; 
	File insert(File file) ;
	boolean deleteByID(Long id) ;
}
