package com.lawencon.assetsystem.service;

import com.lawencon.assetsystem.model.File;

public interface FileService {
	 File getById(Long id); 
	 File insert(File file);
	 boolean delete(Long id);
}
