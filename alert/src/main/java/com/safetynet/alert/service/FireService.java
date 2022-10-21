package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface for Fire Service.
 * Generate a Get Method.
 */
public interface FireService {
    List<FireDTO> getAllPeopleByFireStationAddress(String address);
}
