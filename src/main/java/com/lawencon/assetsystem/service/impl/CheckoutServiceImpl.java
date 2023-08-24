package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;
import static com.lawencon.assetsystem.util.LocalDateTimeUtil.formatDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.constant.TypeCheckout;
import com.lawencon.assetsystem.dao.AssetDao;
import com.lawencon.assetsystem.dao.CheckoutDao;
import com.lawencon.assetsystem.dao.CheckoutTypeDao;
import com.lawencon.assetsystem.dao.EmployeeDao;
import com.lawencon.assetsystem.dao.LocationDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutDetailResDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutInsertReqDto;
import com.lawencon.assetsystem.dto.checkout.CheckoutResDto;
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeInsertReqDto;
import com.lawencon.assetsystem.dto.checkouttype.CheckoutTypeResDto;
import com.lawencon.assetsystem.model.Asset;
import com.lawencon.assetsystem.model.Checkout;
import com.lawencon.assetsystem.model.CheckoutDetails;
import com.lawencon.assetsystem.model.CheckoutType;
import com.lawencon.assetsystem.service.CheckoutService;
import com.lawencon.assetsystem.service.PrincipalService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private final CheckoutDao checkoutDao;
	private final EmployeeDao employeeDao;
	private final LocationDao locationDao;
	private final CheckoutTypeDao checkoutTypeDao;
	private final AssetDao assetDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public CheckoutServiceImpl(CheckoutDao checkoutDao, EmployeeDao employeeDao, LocationDao locationDao,
			AssetDao assetDao, CheckoutTypeDao checkoutTypeDao, PrincipalService principalService) {
		this.checkoutDao = checkoutDao;
		this.employeeDao = employeeDao;
		this.locationDao = locationDao;
		this.assetDao = assetDao;
		this.checkoutTypeDao = checkoutTypeDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public InsertResDto insertCheckout(CheckoutInsertReqDto checkout) {
		final InsertResDto result = new InsertResDto();
		final LocalDateTime now = LocalDateTime.now();

		Checkout newCheckout = new Checkout();
		final String randomCode = generateID(5);
		newCheckout.setCheckoutCode(randomCode);

		final CheckoutType type = new CheckoutType();

		if (checkout.getEmployeeId() != null) {
			newCheckout.setEmployee(employeeDao.getEmployeeById(checkout.getEmployeeId()));
			type.setId(TypeCheckout.EMPLOYEE.getTypeId());
			newCheckout.setCheckoutType(type);
		} 
		
		if (checkout.getLocationId() != null) {
			newCheckout.setLocation(locationDao.getLocationById(checkout.getLocationId()));
			type.setId(TypeCheckout.LOCATION.getTypeId());
			newCheckout.setCheckoutType(type);
		} 
		
		if (checkout.getAssetGeneralId() != null) {
			newCheckout.setAsset(assetDao.getAssetById(checkout.getAssetGeneralId()));
			type.setId(TypeCheckout.GENERAL.getTypeId());
			newCheckout.setCheckoutType(type);
		}
		final CheckoutType checkoutType = checkoutTypeDao.getCheckoutTypeById(checkout.getTypeId());
		newCheckout.setCheckoutType(checkoutType);
		newCheckout.setCreatedBy(principalService.getPrincipal());
		newCheckout = checkoutDao.insert(newCheckout);

		for (int i = 0; i < checkout.getDetails().size(); i++) {
			final CheckoutDetails detail = new CheckoutDetails();
			final Asset assetid = assetDao.getAssetById(checkout.getDetails().get(i).getAssetId());
			detail.setAsset(assetid);

			detail.setCheckout(newCheckout);
			detail.setCheckoutDate(now);
			if (checkout.getDetails().get(i).getDueDate() != null) {
				detail.setDueDate(formatDate(checkout.getDetails().get(i).getDueDate()));
			} else {
				detail.setDueDate(null);
			}
			detail.setCheckinDate(null);
			detail.setCreatedBy(principalService.getPrincipal());
			checkoutDao.insertDetails(detail);
		}

		result.setId(newCheckout.getId());
		result.setMessage("Insert Checkout Success! " + newCheckout.getCheckoutCode());
		return result;
	}

	@Override
	public List<CheckoutResDto> getCheckoutCode() {
		final List<CheckoutResDto> codes = new ArrayList<>();

		checkoutDao.getCheckoutCode().forEach(c -> {
			final CheckoutResDto checkout = new CheckoutResDto();
			checkout.setId(c.getId());
			checkout.setCheckoutCode(c.getCheckoutCode());
			checkout.setTypeName(c.getCheckoutType().getcTypeName());
			
			if (c.getEmployee().getEmployeeName() != null) {
				checkout.setEmployeeName(c.getEmployee().getEmployeeName());
			}
			
			if (c.getLocation().getLocationDetail() != null) {
				checkout.setLocationName(c.getLocation().getLocationDetail());
			}
			
			if (c.getAsset().getAssetName() != null) {
				checkout.setAssetGeneral(c.getAsset().getAssetName());
			}
			
			codes.add(checkout);
		});

		return codes;
	}

	public List<CheckoutDetailResDto> getCheckoutDetails(String checkout) {
		final Checkout checkoutCode = new Checkout();
		checkoutCode.setCheckoutCode(checkout);
		final List<CheckoutDetailResDto> details = new ArrayList<>();

		checkoutDao.getDetailsByCode(checkoutCode).forEach(c -> {
			final CheckoutDetailResDto detail = new CheckoutDetailResDto();
			detail.setId(c.getId());
			detail.setAssetName(c.getAsset().getAssetName());
			detail.setCheckoutDate(c.getCheckoutDate().toString());
			if (c.getDueDate() != null) {
				detail.setDueDate(c.getDueDate().toString());
			}
			
			if (c.getCheckinDate() != null) {
				detail.setCheckinDate(c.getCheckinDate().toString());
			}

			details.add(detail);
		});

		return details;
	}

	@Transactional
	@Override
	public InsertResDto insertCheckoutType(CheckoutTypeInsertReqDto type) {
		InsertResDto result = new InsertResDto();

		CheckoutType newType = null;

		for (int i = 0; i < type.getTypeName().size(); i++) {
			newType = new CheckoutType();
			newType.setcTypeCode(type.getTypeCode().get(i));
			newType.setcTypeName(type.getTypeName().get(i));
			newType.setCreatedBy(principalService.getPrincipal());

			checkoutTypeDao.insert(newType);
		}

		result.setId(newType.getId());
		result.setMessage("Insert Checkout Type Success!");
		return result;
	}

	@Override
	public List<CheckoutTypeResDto> getAllCheckoutType() {
		final List<CheckoutTypeResDto> types = new ArrayList<>();

		checkoutTypeDao.getAll().forEach(t -> {
			final CheckoutTypeResDto type = new CheckoutTypeResDto();
			type.setId(t.getId());
			type.setTypeCode(t.getcTypeCode());
			type.setTypeName(t.getcTypeName());
			types.add(type);
		});

		return types;
	}

	@Override
	public List<CheckoutResDto> getAll() {
		final List<CheckoutResDto> checkouts = new ArrayList<>();

		checkoutDao.getAll().forEach(c -> {
			final CheckoutResDto checkout = new CheckoutResDto();
			checkout.setCheckoutCode(c.getCheckoutCode());
			if (c.getCheckoutType().getcTypeName() != null) {
				checkout.setTypeName(c.getCheckoutType().getcTypeName());
			}
			if (c.getLocation().getLocationDetail() != null) {
				checkout.setLocationName(c.getLocation().getLocationDetail());
			}
			if (c.getAsset().getAssetName() != null) {
				checkout.setAssetGeneral(c.getAsset().getAssetName());
			}

			checkouts.add(checkout);
		});

		return checkouts;
	}

	@Override
	public List<CheckoutResDto> getAllCheckouts() {
		final List<CheckoutResDto> checkouts = new ArrayList<>();

		checkoutDao.getAllCheckout().forEach(c -> {
			final CheckoutResDto checkout = new CheckoutResDto();
			checkout.setCheckoutCode(c.getCheckoutCode());
			if (c.getCheckoutType().getcTypeName() != null) {
				checkout.setTypeName(c.getCheckoutType().getcTypeName());
			}
			if (c.getEmployee().getEmployeeName() != null) {
				checkout.setEmployeeName(c.getEmployee().getEmployeeName());
			}
			if (c.getLocation().getLocationDetail() != null) {
				checkout.setLocationName(c.getLocation().getLocationDetail());
			}
			if (c.getAsset().getAssetName() != null) {
				checkout.setAssetGeneral(c.getAsset().getAssetName());
			}

			checkouts.add(checkout);
		});

		return checkouts;
	}
}
