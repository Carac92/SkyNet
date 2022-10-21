package com.safetynet.alert.service;

import com.safetynet.alert.dto.FloodDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the flood service.
 * Generate a Get Method.
 */
public interface FloodService {
    List<FloodDTO> getAllHousesByAListOfStationNumber(List<Integer> stationsNumber);
}
