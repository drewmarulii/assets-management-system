package com.lawencon.assetsystem.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.assetsystem.dao.LocationDao;
import com.lawencon.assetsystem.model.Location;

@Repository
@Profile("hql-query")
public class LocationDaoHQLImpl implements LocationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Location> getAll() {
		final String sql = "SELECT "
				+ "	l "
				+ "FROM "
				+ "	Location l";
		final List<Location> locations = this.em.createQuery(sql, Location.class)
				.getResultList();
		
		return locations;
	}

	@Override
	public Location insert(Location location) {
		em.persist(location);
		return location;
	}

	@Override
	public Location getLocationById(Long id) {
		final Location location = this.em.find(Location.class, id);
		return location;
	}

}
