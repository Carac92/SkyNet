package com.safetynet.alert.service;

import com.safetynet.alert.dto.PhoneAlertDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Phone Alert service.
 * Generate a get Method.
 */
public interface PhoneAlertService {
    List<PhoneAlertDTO> getAllPhoneForAFireStationNumber(Integer stationNumber);
}
