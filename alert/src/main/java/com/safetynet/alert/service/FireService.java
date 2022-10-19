package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireDTO;

import java.util.List;

public interface FireService {
    List<FireDTO> getAllPeopleByFireStationAddress(String address);
}
