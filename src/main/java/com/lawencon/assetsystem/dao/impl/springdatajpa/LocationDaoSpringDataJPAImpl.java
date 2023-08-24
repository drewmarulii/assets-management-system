package com.lawencon.assetsystem.dao.impl.springdatajpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.LocationDao;
import com.lawencon.assetsystem.model.Location;
import com.lawencon.assetsystem.repo.LocationRepo;

@Repository
@Profile("springdatajpa-query")
public class LocationDaoSpringDataJPAImpl implements LocationDao {
	
	private final LocationRepo locationRepo;

	public LocationDaoSpringDataJPAImpl(LocationRepo locationRepo) {
		this.locationRepo = locationRepo;
	}

	@Override
	public List<Location> getAll() {
		final List<Location> locations = locationRepo.findAll();
		return locations;
	}

	@Override
	public Location insert(Location location) {
		locationRepo.save(location);
		return location;
	}

	@Override
	public Location getLocationById(Long id) {
		final Location location = locationRepo.findById(id).get();
		return location;
	}

}
