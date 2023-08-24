package com.lawencon.assetsystem.service.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.CheckoutDao;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.checkout.CheckinInsertReqDto;
import com.lawencon.assetsystem.model.CheckoutDetails;
import com.lawencon.assetsystem.service.CheckinService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class CheckinServiceImpl implements CheckinService {

	private final CheckoutDao checkoutDao;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public CheckinServiceImpl(CheckoutDao checkoutDao, PrincipalService principalService) {
		this.checkoutDao = checkoutDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public UpdateResDto insertCheckin(CheckinInsertReqDto checkin) {		
		UpdateResDto result = null;
		final LocalDateTime now = LocalDateTime.now();
		
		for (int i = 0; i < checkin.getDetailId().size(); i++) {
			final CheckoutDetails detail = checkoutDao.getDetailById(checkin.getDetailId().get(i));
			detail.setCheckinDate(now);
			detail.setUpdatedBy(principalService.getPrincipal());
			
			em.flush();
			result = new UpdateResDto();
			result.setVersion(detail.getVersion());
			result.setMessage("Total Checked In Assets: " + checkin.getDetailId().size() + " Success");
		}
	
		return result;
	}

}
