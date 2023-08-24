package com.lawencon.assetsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.LocationDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.location.LocationInsertReqDto;
import com.lawencon.assetsystem.dto.location.LocationResDto;
import com.lawencon.assetsystem.dto.location.LocationUpdateReqDto;
import com.lawencon.assetsystem.model.Location;
import com.lawencon.assetsystem.service.LocationService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class LocationServiceImpl implements LocationService {

	private final LocationDao locationDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public LocationServiceImpl(LocationDao locationDao, PrincipalService principalService) {
		this.locationDao = locationDao;
		this.principalService = principalService;
	}

	@Override
	public List<LocationResDto> getAll() {
		final List<LocationResDto> locations = new ArrayList<>();

		locationDao.getAll().forEach(l -> {
			final LocationResDto location = new LocationResDto();
			location.setId(l.getId());
			location.setLocationNumber(l.getLocationNumber());
			location.setLocationDetail(l.getLocationDetail());
			locations.add(location);
		});

		return locations;
	}

	@Transactional
	@Override
	public InsertResDto insert(LocationInsertReqDto location) {
		InsertResDto result = new InsertResDto();

		final Location newLocation = new Location();
		newLocation.setLocationNumber(location.getLocationNumber());
		newLocation.setLocationDetail(location.getLocationDetail());
		newLocation.setCreatedBy(principalService.getPrincipal());
		locationDao.insert(newLocation);

		result.setId(newLocation.getId());
		result.setMessage("Insert Location Success!");
		return result;
	}

	@Override
	public LocationResDto getById(Long id) {
		final Location loc = locationDao.getLocationById(id);
		
		final LocationResDto location = new LocationResDto();
		location.setId(loc.getId());
		location.setLocationNumber(loc.getLocationNumber());
		location.setLocationDetail(loc.getLocationDetail());
		
		return location;
	}

	@Transactional
	@Override
	public UpdateResDto updateLocation(LocationUpdateReqDto location) {
		final Location loc = locationDao.getLocationById(location.getId());
		loc.setLocationNumber(location.getLocationNumber());
		loc.setLocationDetail(location.getLocationDetail());
		loc.setUpdatedBy(principalService.getPrincipal());
		
		em.flush();
		
		final UpdateResDto result = new UpdateResDto();
		result.setVersion(loc.getVersion());
		result.setMessage("Location has been updated");
		
		return result;
	}

}
