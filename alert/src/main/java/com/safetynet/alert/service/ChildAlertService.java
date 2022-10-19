package com.safetynet.alert.service;

import com.safetynet.alert.dto.ChildAlertDTO;

import java.util.List;

public interface ChildAlertService {
    List<ChildAlertDTO> findChildOfAnAddress(String address);
}
