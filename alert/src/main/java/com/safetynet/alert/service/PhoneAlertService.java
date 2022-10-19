package com.safetynet.alert.service;

import com.safetynet.alert.dto.PhoneAlertDTO;

import java.util.List;

public interface PhoneAlertService {
    List<PhoneAlertDTO> getAllPhoneForAFireStationNumber(Integer stationNumber);
}
