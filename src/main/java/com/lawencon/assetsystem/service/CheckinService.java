package com.lawencon.assetsystem.service;

import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.checkout.CheckinInsertReqDto;

public interface CheckinService {
	UpdateResDto insertCheckin(CheckinInsertReqDto checkin);
}
