package com.lawencon.assetsystem.dao;

import java.util.List;

import com.lawencon.assetsystem.model.Location;

public interface LocationDao {
	List<Location> getAll();
	Location insert(Location location);
	Location getLocationById(Long id);
}
