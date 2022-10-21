package com.safetynet.alert.service;

import com.safetynet.alert.dto.ChildAlertDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Service of Child Alert.
 * Generate a get Method.
 */
public interface ChildAlertService {
    List<ChildAlertDTO> findChildOfAnAddress(String address);
}
